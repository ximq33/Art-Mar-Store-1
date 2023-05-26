// @flow
import React, {useRef, useState} from 'react';
import {Button, Card, Col, Container, Form, FormGroup, ProgressBar, Row} from 'react-bootstrap';
import {Step, Steps, Wizard} from 'react-albus';
import * as yup from 'yup';
import {yupResolver} from '@hookform/resolvers/yup';

// components
import PageTitle from '../../components/PageTitle';
import {FormInput, VerticalForm} from '../../components/';
import FileUploader from "../../components/FileUploader";
import {Link, redirect} from "react-router-dom";
import FormCheckInput from "react-bootstrap/FormCheckInput";
import * as Yup from "yup";
import Nouislider from "nouislider-react";
import Slider from "../uikit/Slider";

const BasicWizard = () => {
    return (
        <Card>
            <Card.Body>
                <h4 className="header-title mb-3"> Basic Wizard</h4>

                <Wizard>
                    <Steps>
                        <Step
                            id="login"
                            render={({next}) => (
                                <Form>
                                    <Form.Group as={Row} className="mb-3">
                                        <Form.Label htmlFor="exampleEmail" column md={3}>
                                            Email
                                        </Form.Label>
                                        <Col md={9}>
                                            <Form.Control
                                                type="email"
                                                name="exampleEmail"
                                                id="exampleEmail"
                                                placeholder="Enter email"
                                            />
                                        </Col>
                                    </Form.Group>

                                    <Form.Group as={Row} className="mb-3">
                                        <Form.Label htmlFor="examplePassword" column md={3}>
                                            Password
                                        </Form.Label>
                                        <Col md={9}>
                                            <Form.Control
                                                type="password"
                                                name="examplePassword"
                                                id="examplePassword"
                                                placeholder="password placeholder"
                                                defaultValue="12345"
                                            />
                                        </Col>
                                    </Form.Group>

                                    <Form.Group as={Row} className="mb-3">
                                        <Form.Label htmlFor="examplerePassword" column md={3}>
                                            Re-Password
                                        </Form.Label>
                                        <Col md={9}>
                                            <Form.Control
                                                type="password"
                                                name="exampleRepassword"
                                                id="examplerePassword"
                                                placeholder="password"
                                                defaultValue="12345"
                                            />
                                        </Col>
                                    </Form.Group>

                                    <ul className="list-inline wizard mb-0">
                                        <li className="next list-inline-item float-end">
                                            <Button onClick={next} variant="success">
                                                Next
                                            </Button>
                                        </li>
                                    </ul>
                                </Form>
                            )}
                        />
                        <Step
                            id="gandalf"
                            render={({next, previous}) => (
                                <Form>
                                    <Form.Group as={Row} className="mb-3">
                                        <Form.Label htmlFor="fname" column md={3}>
                                            First Name
                                        </Form.Label>
                                        <Col md={9}>
                                            <Form.Control
                                                type="text"
                                                name="fname"
                                                id="fname"
                                                placeholder="Enter first name"
                                            />
                                        </Col>
                                    </Form.Group>

                                    <Form.Group as={Row} className="mb-3">
                                        <Form.Label htmlFor="lname" column md={3}>
                                            Last Name
                                        </Form.Label>
                                        <Col md={9}>
                                            <Form.Control
                                                type="text"
                                                name="lname"
                                                id="lname"
                                                placeholder="enter last name"
                                            />
                                        </Col>
                                    </Form.Group>

                                    <Form.Group as={Row} className="mb-3">
                                        <Form.Label htmlFor="phone" column md={3}>
                                            Phone Number
                                        </Form.Label>
                                        <Col md={9}>
                                            <Form.Control
                                                type="text"
                                                name="phone"
                                                id="phone"
                                                placeholder="enter phone number"
                                            />
                                        </Col>
                                    </Form.Group>

                                    <ul className="list-inline wizard mb-0">
                                        <li className="previous list-inline-item">
                                            <Button onClick={previous} variant="info">
                                                Previous
                                            </Button>
                                        </li>
                                        <li className="next list-inline-item float-end">
                                            <Button onClick={next} variant="success">
                                                Next
                                            </Button>
                                        </li>
                                    </ul>
                                </Form>
                            )}
                        />
                        <Step
                            id="dumbledore"
                            render={({previous}) => (
                                <Row>
                                    <Col sm={12}>
                                        <div className="text-center">
                                            <h2 className="mt-0">
                                                <i className="mdi mdi-check-all"></i>
                                            </h2>
                                            <h3 className="mt-0">Thank you !</h3>

                                            <p className="w-75 mb-2 mx-auto">
                                                Quisque nec turpis at urna dictum luctus. Suspendisse convallis
                                                dignissim eros at volutpat. In egestas mattis dui. Aliquam mattis dictum
                                                aliquet.
                                            </p>

                                            <div className="mb-3">
                                                <Form.Check type="checkbox" className="d-inline-block">
                                                    <Form.Check.Input type="checkbox"/>{' '}
                                                    <Form.Check.Label>
                                                        I agree with the Terms and Conditions
                                                    </Form.Check.Label>
                                                </Form.Check>
                                            </div>
                                        </div>
                                    </Col>

                                    <Col sm={12}>
                                        <ul className="list-inline wizard mb-0">
                                            <li className="previous list-inline-item">
                                                <Button onClick={previous} variant="info">
                                                    Previous
                                                </Button>
                                            </li>

                                            <li className="next list-inline-item float-end">
                                                <Button variant="success">Submit</Button>
                                            </li>
                                        </ul>
                                    </Col>
                                </Row>
                            )}
                        />
                    </Steps>
                </Wizard>
            </Card.Body>
        </Card>
    );
};

const WizardWithProgressbar = () => {
    return (

        <Card>
            <Card.Body>
                <h4 className="header-title mb-3">Wizard with Progress bar</h4>

                <Wizard
                    render={({step, steps}) => (
                        <>
                            <ProgressBar
                                animated
                                striped
                                variant="success"
                                now={((steps.indexOf(step) + 1) / steps.length) * 100}
                                className="mb-3 progress-sm"
                            />

                            <Steps>
                                <Step
                                    id="login"
                                    render={({next}) => (
                                        <Form>
                                            <Form.Group as={Row} className="mb-3">
                                                <Form.Label htmlFor="exampleEmail" column md={3}>
                                                    Email
                                                </Form.Label>
                                                <Col md={9}>
                                                    <Form.Control
                                                        type="email"
                                                        name="exampleEmail"
                                                        id="exampleEmail2"
                                                        placeholder="Enter email"
                                                    />
                                                </Col>
                                            </Form.Group>

                                            <Form.Group as={Row} className="mb-3">
                                                <Form.Label htmlFor="examplePassword" column md={3}>
                                                    Password
                                                </Form.Label>
                                                <Col md={9}>
                                                    <Form.Control
                                                        type="password"
                                                        name="password"
                                                        id="examplePassword2"
                                                        placeholder="password placeholder"
                                                        defaultValue="12345"
                                                    />
                                                </Col>
                                            </Form.Group>

                                            <Form.Group as={Row} className="mb-3">
                                                <Form.Label htmlFor="examplerePassword" column md={3}>
                                                    Re-Password
                                                </Form.Label>
                                                <Col md={9}>
                                                    <Form.Control
                                                        type="password"
                                                        name="repassword"
                                                        id="examplerePassword2"
                                                        placeholder="password"
                                                        defaultValue="12345"
                                                    />
                                                </Col>
                                            </Form.Group>

                                            <ul className="list-inline wizard mb-0">
                                                <li className="next list-inline-item float-end">
                                                    <Button onClick={next} variant="success">
                                                        Next
                                                    </Button>
                                                </li>
                                            </ul>
                                        </Form>
                                    )}
                                />
                                <Step
                                    id="gandalf"
                                    render={({next, previous}) => (
                                        <Form>
                                            <Form.Group as={Row} className="mb-3">
                                                <Form.Label htmlFor="fname" column md={3}>
                                                    First Name
                                                </Form.Label>
                                                <Col md={9}>
                                                    <Form.Control
                                                        type="text"
                                                        name="fname"
                                                        id="fname"
                                                        placeholder="Enter first name"
                                                    />
                                                </Col>
                                            </Form.Group>

                                            <Form.Group as={Row} className="mb-3">
                                                <Form.Label htmlFor="lname" column md={3}>
                                                    Last Name
                                                </Form.Label>
                                                <Col md={9}>
                                                    <Form.Control
                                                        type="text"
                                                        name="lname"
                                                        id="lname"
                                                        placeholder="enter last name"
                                                    />
                                                </Col>
                                            </Form.Group>

                                            <Form.Group as={Row} className="mb-3">
                                                <Form.Label htmlFor="phone" column md={3}>
                                                    Phone Number
                                                </Form.Label>
                                                <Col md={9}>
                                                    <Form.Control
                                                        type="text"
                                                        name="phone"
                                                        id="phone"
                                                        placeholder="enter phone number"
                                                    />
                                                </Col>
                                            </Form.Group>

                                            <ul className="list-inline wizard mb-0">
                                                <li className="previous list-inline-item">
                                                    <Button onClick={previous} variant="info">
                                                        Previous
                                                    </Button>
                                                </li>
                                                <li className="next list-inline-item float-end">
                                                    <Button onClick={next} variant="success">
                                                        Next
                                                    </Button>
                                                </li>
                                            </ul>
                                        </Form>
                                    )}
                                />
                                <Step
                                    id="dumbledore"
                                    render={({previous}) => (
                                        <Row>
                                            <Col sm={12}>
                                                <div className="text-center">
                                                    <h2 className="mt-0">
                                                        <i className="mdi mdi-check-all"></i>
                                                    </h2>
                                                    <h3 className="mt-0">Thank you !</h3>

                                                    <p className="w-75 mb-2 mx-auto">
                                                        Quisque nec turpis at urna dictum luctus. Suspendisse
                                                        convallis
                                                        dignissim eros at volutpat. In egestas mattis dui.
                                                        Aliquam
                                                        mattis dictum aliquet.
                                                    </p>

                                                    <div className="mb-3">
                                                        <Form.Check type="checkbox" className="d-inline-block">
                                                            <Form.Check.Input type="checkbox"/>{' '}
                                                            <Form.Check.Label>
                                                                I agree with the Terms and Conditions
                                                            </Form.Check.Label>
                                                        </Form.Check>
                                                    </div>
                                                </div>
                                            </Col>

                                            <Col sm={12}>
                                                <ul className="list-inline wizard mb-0">
                                                    <li className="previous list-inline-item">
                                                        <Button onClick={previous} variant="info">
                                                            Previous
                                                        </Button>
                                                    </li>

                                                    <li className="next list-inline-item float-end">
                                                        <Button variant="success">Submit</Button>
                                                    </li>
                                                </ul>
                                            </Col>
                                        </Row>
                                    )}
                                />
                            </Steps>
                        </>
                    )}
                />
            </Card.Body>
        </Card>

    );
};

export const WizardWithFormValidation = () => {

    const [variantList, setVariantList] = useState([{
        id: null,
        colorName: null,
        RGB: null,
        left: true,
        right: true
    }]);
    const [allImages, setAllImages] = useState([]);
    const [variantCount, setVariantCount] = useState(0);
    const [variantImages, setVariantImages] = useState([]);
    const [error, setError] = useState("");
    const [leftWidthValues, setLeftWidthValues] = useState([]);
    const [selectedVals, setSelectedVals] = useState(60);




    const header = (stepId) => {
        switch (stepId) {
            case "addProduct": return "dodaj nowy produkt";
            case "variants": return "dodaj warianty";
            case "addVariant": return "dodaj wariant";
            case "addWidth": return "ustaw szerokości";
        }
    }


    const onSlideFunc = (value) => {
        console.log(value)
        setLeftWidthValues([...leftWidthValues, value])
        console.log("dupson " + leftWidthValues)
        setSelectedVals(value)
    }


    const handleAddVariantClick = (values) => {
        setAllImages(oldArray => [...oldArray, variantImages]);
        setVariantList(prevList => {
            const updatedList = [...prevList];
            updatedList[variantCount].id = variantCount;
            updatedList[variantCount].colorName = values.target.colorName.value;
            updatedList[variantCount].RGB = values.target.RGB.value;
             }
            )
        console.log(variantList)
        setVariantCount(variantCount + 1);
    }


    const validationSchema = yupResolver(
        yup.object().shape({
            name: yup.string().required('Podaj nazwę produktu'),
            producer: yup.string().required('Podaj producenta'),
            description: yup.string().required('Opisz produkt'),
        })
    );
    const validationSchema2 = yupResolver(
        yup.object().shape({
            firstname: yup.string().required('Please enter First Name'),
            lastname: yup.string().required('Please enter Last Name'),
            checkbox: yup.bool().oneOf([true]),
        })
    );


    const validationSchema3 = yupResolver(
        yup.object().shape({
            colorName: Yup.string().required('Podaj nazwę koloru'),
            RGB: yup.string().required('Zaznacz kolor'),
        })
    );

    const validationSchema4 = yupResolver(
        yup.object().shape({
            left: yup.bool(),
            right: yup.bool(),
        })
            .test((obj) => {
                setError("");
                if (obj.left || obj.right){
                    return true;
                }
                setError("Zaznacz conajmniej jedną opcję")
                return false
            })
    );




    return (

        <Wizard
            render={({step, steps}) => (
                <Container className="wizard-container">
                    <Card className={`wizard-inner-container ${step.id === 'addVariant' ? 'add-variant' : ''}`}>
                        <Card.Body>
                            <h4 className="header-title mb-3">{header(step.id)}</h4>
                            <ProgressBar
                                animated
                                striped
                                variant="success"
                                now={((steps.indexOf(step) + 1) / steps.length) * 100}
                                className="mb-3 progress-sm"
                            />

                            <Steps>

                                <Step
                                    id="addProduct"
                                    render={({next}) => (
                                        <VerticalForm onSubmit={(event, values) => next()}
                                                      resolver={validationSchema}>
                                            <FormInput
                                                label="Nazwa produktu"
                                                type="text"
                                                name="name"
                                                containerClass={'my-3'}
                                            />
                                            <FormInput
                                                label="Producent"
                                                type="text"
                                                name="producer"
                                                containerClass={'mb-3'}
                                            />
                                            <FormInput
                                                label="Opis"
                                                type="textarea"
                                                name="description"
                                                containerClass={'mb-3'}
                                            />


                                            <ul className="list-inline wizard mb-0">
                                                <li className="next list-inline-item float-end">
                                                    <Button variant="success" type="submit">
                                                        Dalej
                                                    </Button>
                                                </li>
                                                <li>
                                                    <Link to={"/products"}>
                                                    <Button variant="danger">Wyjdź</Button>
                                                    </Link>
                                                </li>
                                            </ul>
                                        </VerticalForm>

                                    )}
                                />
                                <Step
                                    id="variants"
                                    render={({next, previous}) => (
                                        <VerticalForm onSubmit={(event, values) => next()}
                                                      resolver={validationSchema2}>


                                            <Button onClick={next} variant="success"
                                                    className="btn btn-success my-2 mx-1"><i
                                                className="uil-plus font-size-40px"/></Button>

                                            <ul className="list-inline wizard mb-0 mt-5">
                                                <li className="previous list-inline-item">
                                                    <Button onClick={previous} variant="info">
                                                        Wstecz
                                                    </Button>
                                                </li>
                                                <li className="next list-inline-item float-end">
                                                    <Button variant="success" type="submit">
                                                        Zakończ
                                                    </Button>
                                                </li>
                                            </ul>
                                        </VerticalForm>
                                    )}
                                />
                                <Step
                                    id="addVariant"
                                    render={({next, previous}) => (
                                        <div className="add-variant">

                                            <VerticalForm onSubmit={(event, values) => {
                                                handleAddVariantClick(values);
                                                next();}}
                                                          resolver={validationSchema3}>

                                                <FileUploader
                                                    accept="image/*"
                                                    onFileUpload={(files) => {
                                                        const costam = variantCount;
                                                        const fileArray = files.map(file => ({
                                                            variantCounter: costam,
                                                            file: file,
                                                        }));
                                                        setVariantImages(fileArray);
                                                    }}
                                                />

                                                <FormInput type="color"
                                                           name="RGB"
                                                           label="Zaznacz kolor"
                                                           id="html5colorpicker"
                                                           containerClass={'my-3 justify-content-center'}
                                                           className="colorPicker overlay"
                                                            />

                                                    <FormInput
                                                        label="Nazwa koloru"
                                                        type="text"
                                                        name="colorName"
                                                        containerClass={'mb-3'}
                                                    />

                                                        <ul className="list-inline wizard my-2">
                                                            <li className="previous list-inline-item" >
                                                                <Button onClick={previous} variant="info">
                                                                    Wstecz
                                                                </Button>
                                                            </li>
                                                         <li className="next list-inline-item float-end">
                                                              <Button variant="success" type="submit">
                                                                    Dalej
                                                             </Button>
                                                          </li>
                                                      </ul>


                                            </VerticalForm>
                                        </div>
                                        )}
                                    />
                                <Step
                                    id="addWidth"
                                    render={({next, previous}) => (
                                        <div className="add-variant">

                                            <VerticalForm onSubmit={(event, values) => {
                                                handleAddVariantClick(values);
                                                next();}}
                                                          resolver={validationSchema4}>

                                                <FormInput
                                                    name="left"
                                                    defaultChecked="true"
                                                    type="checkbox"
                                                    containerClass={'form-check-inline mx-1 mb-3 col-5'}
                                                    label="Lewe"
                                                />
                                                <FormInput
                                                    name="right"
                                                    defaultChecked="true"
                                                    type="checkbox"
                                                    containerClass={'form-check-inline mx-1 col-5'}
                                                    label="Prawe"
                                                />

                                                <Nouislider
                                                    range={{min: 60, max: 100}}
                                                    start={[60]}
                                                    step={1}
                                                    connect
                                                    onSlide={(value) => onSlideFunc(value)}
                                                />
                                                <p className="mt-2 mb-0">
                                                    {selectedVals ? (
                                                        <span>{selectedVals}</span>) : null}
                                                </p>


                                                {/*<FormInput*/}
                                                {/*    id="gowno"*/}
                                                {/*    type="range"*/}
                                                {/*    label="Szerokość"*/}
                                                {/*    min="60"*/}
                                                {/*    max="100"*/}
                                                {/*    step="1"*/}
                                                {/*    onChange={(value) => setLeftWidthValues(value)}*/}
                                                {/*    containerClass={'form-check-inline col-5 border-0'}*/}
                                                {/*/>*/}
                                                {/*<FormInput*/}
                                                {/*    type="range"*/}
                                                {/*    label="Szerokość"*/}
                                                {/*    min="60"*/}
                                                {/*    max="100"*/}
                                                {/*    step="1"*/}
                                                {/*    containerClass={'form-check-inline col-5 border-0'}*/}
                                                {/*/>*/}

                                                {error ? (
                                                    <p className="text-danger m-0">{error}</p>
                                                ) : <p></p>}


                                                <ul className="list-inline wizard m-1">
                                                    <li className="previous list-inline-item" >
                                                        <Button onClick={previous} variant="info">
                                                            Wstecz
                                                        </Button>
                                                    </li>
                                                    <li className="next list-inline-item float-end">
                                                        <Button variant="success" type="submit">
                                                            Dalej
                                                        </Button>
                                                    </li>
                                                </ul>


                                            </VerticalForm>
                                        </div>

                                    )}
                                />
                                <Step
                                    id="finished"
                                    render={({previous}) => (
                                        <Row>
                                            <Col sm={12}>
                                                <div className="text-center">
                                                    <h2 className="mt-0">
                                                        <i className="mdi mdi-check-all"></i>
                                                    </h2>
                                                    <h3 className="mt-0">Thank you !</h3>

                                                    <p className="w-75 mb-2 mx-auto">
                                                        Quisque nec turpis at urna dictum luctus. Suspendisse
                                                        convallis
                                                        dignissim eros at volutpat. In egestas mattis dui.
                                                        Aliquam
                                                        mattis dictum aliquet.
                                                    </p>

                                                    <div className="mb-3">
                                                        <Form.Check type="checkbox" className="d-inline-block">
                                                            <Form.Check.Input type="checkbox"/>{' '}
                                                            <Form.Check.Label>
                                                                I agree with the Terms and Conditions
                                                            </Form.Check.Label>
                                                        </Form.Check>
                                                    </div>
                                                </div>
                                            </Col>

                                            <Col sm={12}>
                                                <ul className="list-inline wizard mb-0">
                                                    <li className="previous list-inline-item">
                                                        <Button onClick={previous} variant="info">
                                                            Previous
                                                        </Button>
                                                    </li>

                                                    <li className="next list-inline-item float-end">
                                                        <Button variant="success">Submit</Button>
                                                    </li>
                                                </ul>
                                            </Col>
                                        </Row>
                                    )}
                                />
                            </Steps>
                        </Card.Body>
                    </Card>
                </Container>
            )}
        />

);
};

const FormWizard = (): React$Element
    <React$FragmentType> => {
        return (
        <>
        <PageTitle
        breadCrumbItems={[
    {label: 'Forms', path: '/ui/forms/wizard'},
    {label: 'Form Wizard', path: '/ui/forms/wizard', active: true},
        ]}
        title={'Form Wizard'}
        />

        <Row>
        <Col xl={6}>
        <BasicWizard/>
        </Col>

        <Col xl={6}>
        <WizardWithProgressbar/>
        </Col>
        </Row>

        <Row>
        <Col lg={6}>
        <WizardWithFormValidation/>
        </Col>
        </Row>
        </>
        );
    };

        export default FormWizard;
