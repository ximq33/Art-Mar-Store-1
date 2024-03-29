import React, {useState} from 'react';
import {Alert, Button, Col, Row} from 'react-bootstrap';
import {Link, Navigate, useLocation} from 'react-router-dom';
import * as yup from 'yup';
import {yupResolver} from '@hookform/resolvers/yup';
import {useTranslation} from 'react-i18next';
import {getUser, setJwtToken} from '../../utils/ApiCalls';
import {FormInput, VerticalForm} from '../../components/';
import AccountLayout from './AccountLayout';

const BottomLink = () => {
    const {t} = useTranslation();

    return (
        <Row className="mt-3">
            <Col className="text-center">
                <p className="text-muted">
                    {t("Nie posiadasz konta?")}{' '}
                    <Link to={'/account/register'} className="text-muted ms-1">
                        <b>{t('Zarejestruj się')}</b>
                    </Link>
                </p>
            </Col>
        </Row>
    );
};

const Login = (): React$Element<any> => {
    const {t} = useTranslation();
    const [authority, setAuthority] = useState(null);
    const [err, setErr] = useState(null);
    const location = useLocation();
    const [redirectUrl, setRedirectUrl] = useState(null);
    const [loading, setLoading] = useState(false);


    /*
    form validation schema
    */
    const schemaResolver = yupResolver(
        yup.object().shape({
            username: yup.string().required(t('Wprowadź poprawną nazwę użytkownika')),
            password: yup.string().required(t('Wprowadź poprawne hasło')),
        })
    );

    /*
    handle form submission
    */
    const onSubmit = async (formData) => {
        setLoading(true);

        const response = await fetch(process.env.REACT_APP_API_URL + "token", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                'Authorization': 'Basic ' + btoa(formData['username'] + ':' + formData['password'])
            }
        })
        if (response.status >= 400) {
            setErr("Niepoprawne dane logowania");
            setLoading(false);
            return;
        }
        if (response.ok) {
            const responseJson = await response.json()
            await setJwtToken(responseJson.value)
            const userResponse = await getUser()
            const userResponseJson = await userResponse.json()

            if (userResponseJson.authorities[0].authority === "ADMIN") {
                setRedirectUrl(location.state && location.state.from ? location.state.from.pathname : '/dashboard/ecommerce')
            } else {
                setRedirectUrl('account/register')
            }
            setAuthority(true)
            setLoading(false);
        }
    }


return (
    <>
        {(authority) && <Navigate to={redirectUrl} replace/>}

        <AccountLayout bottomLinks={<BottomLink/>}>
            <div className="text-center w-75 m-auto">
                <h4 className="text-dark-50 text-center mt-0 fw-bold">{t('Zaloguj się')}</h4>
                <p className="text-muted mb-4">
                    {t('Za pomocą nazwy użytkownika lub adresu e-mail')}
                </p>
            </div>

            {err && (
                <Alert variant="danger" className="my-2">
                    {err}
                </Alert>
            )}

            <VerticalForm
                onSubmit={onSubmit}
                resolver={schemaResolver}>
                <FormInput
                    label={t('Nazwa lub e-mail')}
                    type="text"
                    name="username"
                    placeholder={t('Podaj nazwę lub e-mail')}
                    containerClass={'mb-3'}
                />
                <FormInput
                    label={t('Hasło')}
                    type="password"
                    name="password"
                    placeholder={t('Podaj hasło')}
                    containerClass={'mb-3'}>
                    <Link to="/account/forget-password" className="text-muted float-end">
                        <small>{t('Odzyskaj hasło')}</small>
                    </Link>
                </FormInput>

                <div className="mb-3 mb-0 text-center">
                    <Button variant="dark" type="submit" disabled={loading}>
                        {t('Zaloguj')}
                    </Button>
                </div>
            </VerticalForm>
        </AccountLayout>
    </>
);
}
;

export default Login;
