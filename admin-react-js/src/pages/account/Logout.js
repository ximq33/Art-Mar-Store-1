// @flow
import React, { useEffect } from 'react';
import { Link } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { useTranslation } from 'react-i18next';
import { Row, Col } from 'react-bootstrap';

//actions
import { logoutUser } from '../../redux/actions';

// components
import AccountLayout from './AccountLayout';

// images
import logoutIcon from '../../assets/images/logout-icon.svg';
import {logout} from "../../utils/ApiCalls";

/* bottom link */
const BottomLink = () => {
    const { t } = useTranslation();

    return (
        <Row className="mt-3">
            <Col className="text-center">
                <p className="text-muted">
                    {t('Wróć do ')}{' '}
                    <Link to={'/account/login'} className="text-muted ms-1">
                        <b>{t('Zaloguj się')}</b>
                    </Link>
                </p>
            </Col>
        </Row>
    );
};

const Logout = (): React$Element<any> | React$Element<React$FragmentType> => {
    const { t } = useTranslation();
    const dispatch = useDispatch();

    useEffect(() => {
        logout();
    }, []);

    return (
        <>
            <AccountLayout bottomLinks={<BottomLink />}>
                <div className="text-center w-75 m-auto">
                    <h4 className="text-dark-50 text-center mt-0 fw-bold">{t('Do zobaczenia !')}</h4>
                    <p className="text-muted mb-4">{t('Wylogowano pomyślnie')}</p>

                    <div className="logout-icon m-auto">
                        <img src={logoutIcon} alt="" />
                    </div>
                </div>
            </AccountLayout>
        </>
    );
};

export default Logout;
