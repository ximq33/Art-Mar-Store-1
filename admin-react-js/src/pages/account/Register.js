// @flow
import React, { useState } from "react";
import { Link } from "react-router-dom";
import { Alert, Button, Col, Row } from "react-bootstrap";
import { useTranslation } from "react-i18next";
import * as yup from "yup";
import { yupResolver } from "@hookform/resolvers/yup";
//actions

// components
import { FormInput, VerticalForm } from "../../components/";

import AccountLayout from "./AccountLayout";


/* bottom link */
const BottomLink = () => {
  const { t } = useTranslation();

  return (
    <Row className="mt-3">
      <Col className="text-center">
        <p className="text-muted">
          {t("Posiadasz już konto?")}{" "}
          <Link to={"/account/login"} className="text-muted ms-1">
            <b>{t("Zaloguj się")}</b>
          </Link>
        </p>
      </Col>
    </Row>
  );
};

const Register = (): React$Element<React$FragmentType> => {
  const { t } = useTranslation();


  const [err, setErr] = useState(null);


  /*
   * form validation schema
   */
  const schemaResolver = yupResolver(
    yup.object().shape({
      name: yup.string().required(t("Proszę wprowadzić nazwę użytkownika"))
        .min(3, "Nazwa musi mieć conajmniej 3 znaki")
        .max(30, "Nazwa nie może mieć więcej niż 30 znaków"),
      email: yup.string().required("Prosze wprowadzić e-mail")
        .email("E-mail nieprawidłowy"),
      password: yup.string().required(t("Prosze wprowadzić hasło"))
        .min(8, "Hasło musi mieć conajmniej 8 znaków")
    })
  );

  /*
   * handle form submission
   */
  const onSubmit = (formData) => {
    fetch(process.env.REACT_APP_API_URL + "users", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(formData)
    })
      .then((response) => {
        if (response.status === 409) {
          return response.json().then(response => {
            setErr(response.message);
          });
        } else
          window.location.href = "http://localhost:3000/account/login";
        // handle successful response

      });

  };

  return (
    <>


      <AccountLayout bottomLinks={<BottomLink />}>
        <div className="text-center w-75 m-auto">
          <h4 className="text-dark-50 text-center mt-0 mb-4 fw-bold">{t("Zarejestruj się")}</h4>

        </div>

        {err && (
          <Alert variant="danger" className="my-2">
            {err}
          </Alert>
        )}

        <VerticalForm onSubmit={onSubmit} resolver={schemaResolver} defaultValues={{}}>
          <FormInput
            label={t("Nazwa użytkownika")}
            type="text"
            name="name"
            placeholder={t("Wprowadź nazwę użytkownika")}
            containerClass={"mb-3"}
          />
          <FormInput
            label={t("Adres e-mail")}
            type="email"
            name="email"
            placeholder={t("Wprowadź adres e-mail")}
            containerClass={"mb-3"}
          />
          <FormInput
            label={t("Hasło")}
            type="password"
            name="password"
            placeholder={t("Wprowadź swoje hasło")}
            containerClass={"mb-3"}
          />


          <div className="mb-3 mb-0 text-center">
            <Button variant="dark" type="submit">
              {t("Zarejestruj")}
            </Button>
          </div>
        </VerticalForm>
      </AccountLayout>
    </>
  );
};

export default Register;
