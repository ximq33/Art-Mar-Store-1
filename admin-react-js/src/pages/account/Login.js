// @flow
import React, {useEffect, useState} from 'react';
import {Alert, Button, Col, Row} from 'react-bootstrap';
import {Link, Navigate, useLocation} from 'react-router-dom';
import * as yup from 'yup';
import {yupResolver} from '@hookform/resolvers/yup';
import {useTranslation} from 'react-i18next';
import {useDispatch, useSelector} from 'react-redux';

import {getUser, getCookie, setJwtToken, getJwtToken} from '../../utils/ApiCalls';

//actions
import {loginUser, resetAuth} from '../../redux/actions';

// components
import {FormInput, VerticalForm} from '../../components/';

import AccountLayout from './AccountLayout';

/* bottom link of account pages */
const BottomLink = () => {
    const {t} = useTranslation();

    return (
        <Row className="mt-3">
            <Col className="text-center">
                <p className="text-muted">
                    {t("Don't have an account?")}{' '}
                    <Link to={'/account/register'} className="text-muted ms-1">
                        <b>{t('Sign Up')}</b>
                    </Link>
                </p>
            </Col>
        </Row>
    );
};

const Login = (): React$Element<any> => {
    const {t} = useTranslation();
    const dispatch = useDispatch();
    const [authority, setAuthority] = useState(null);
    const [err, setErr] = useState(null);


    const location = useLocation();
    const [redirectUrl, setRedirectUrl] = useState(null);

    useEffect(() => {
        dispatch(resetAuth());
    }, [dispatch]);

    const {loading, userLoggedIn, user, error} = useSelector((state) => ({
        loading: state.Auth.loading,
        user: state.Auth.user,
        error: state.Auth.error,
        userLoggedIn: state.Auth.userLoggedIn,
    }));

    /*
    form validation schema
    */
    const schemaResolver = yupResolver(
        yup.object().shape({
            username: yup.string().required(t('Please enter Username')),
            password: yup.string().required(t('Please enter Password')),
        })
    );

    /*
    handle form submission
    */
    const onSubmit = (formData) => {

        fetch(process.env.REACT_APP_API_URL + "token", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                'Authorization': 'Basic ' + btoa(formData['username'] + ':' + formData['password'])
            }
        })
            .then((response) => {
                if (response.status >= 400) {
                        setErr("Niepoprawne dane logowania");
                        return;
                }
                if (response.ok) {
                    response.json().then(response => setJwtToken(response.value))
                    console.log(getJwtToken())
                    getUser()
                        .then(r => r.json()
                            .then(r => {
                                if (r.authorities[0].authority === "ADMIN") {
                                    setRedirectUrl(location.state && location.state.from ? location.state.from.pathname : '/dashboard/ecommerce')
                                } else {
                                    setRedirectUrl('/account/register')
                                }
                                setAuthority(r.authorities[0].authority);
                            }));
                }

            })
            .catch((error) => {
                console.error('Error:', error);
            });
    };


    return (
        <>
            {(authority) && <Navigate to={redirectUrl} replace/>}

            <AccountLayout bottomLinks={<BottomLink/>}>
                <div className="text-center w-75 m-auto">
                    <h4 className="text-dark-50 text-center mt-0 fw-bold">{t('Sign In')}</h4>
                    <p className="text-muted mb-4">
                        {t('Enter your email address and password to access admin panel.')}
                    </p>
                </div>

                {err && (
                    <Alert variant="danger" className="my-2">
                        {err}
                    </Alert>
                )}

                <VerticalForm
                    onSubmit={onSubmit}
                    resolver={schemaResolver}
                    defaultValues={{username: 'test', password: 'test'}}>
                    <FormInput
                        label={t('Username')}
                        type="text"
                        name="username"
                        placeholder={t('Podaj email')}
                        containerClass={'mb-3'}
                    />
                    <FormInput
                        label={t('Password')}
                        type="password"
                        name="password"
                        placeholder={t('Enter your password')}
                        containerClass={'mb-3'}>
                        <Link to="/account/forget-password" className="text-muted float-end">
                            <small>{t('Forgot your password?')}</small>
                        </Link>
                    </FormInput>

                    <div className="mb-3 mb-0 text-center">
                        <Button variant="dark" type="submit" disabled={loading}>
                            {t('Log In')}
                        </Button>
                    </div>
                </VerticalForm>
            </AccountLayout>
        </>
    );
};

export default Login;
