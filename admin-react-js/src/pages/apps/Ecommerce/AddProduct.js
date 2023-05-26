import {yupResolver} from "@hookform/resolvers/yup";
import * as yup from "yup";
import * as Yup from "yup";
import {Button, Card, Col, Container, Form, FormGroup, ProgressBar, Row} from "react-bootstrap";
import {Step, Steps, Wizard} from "react-albus";
import {FormInput, VerticalForm} from "../../../components";
import React, {useState} from "react";
import {Link} from "react-router-dom";
import FileUploader from "../../../components/FileUploader";
import "./wizard.css";


const AddProduct = (): React$Element<React$FragmentType> => {
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
    const [selectedValLeft, setSelectedValLeft] = useState(80);
    const [selectedValRight, setSelectedValRight] = useState(80);
    const [leftChecked, setLeftChecked] = useState(true);
    const [rightChecked, setRightChecked] = useState(true);

    const header = (stepId) => {
        switch (stepId) {
            case "addProduct":
                return "dodaj nowy produkt";
            case "variants":
                return "dodaj warianty";
            case "addVariant":
                return "dodaj wariant";
            case "addWidth":
                return "dodaj szerokości";
        }
    }


    const onSlideFunc = (value) => {
        console.log(value)
        setLeftWidthValues([...leftWidthValues, value])
        console.log("dupson " + leftWidthValues)
        setSelectedVals2(value)
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
                if (leftChecked || rightChecked) {
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
                                className="mb-3 progress-md"
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
                                                next();
                                            }}
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
                                                    <li className="previous list-inline-item">
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
                                                next();
                                            }}
                                                          resolver={validationSchema4}>

                                                <FormInput
                                                    name="left"
                                                    defaultChecked="true"
                                                    type="checkbox"
                                                    containerClass={'form-check-inline mb-3 col-md-5'}
                                                    label="Lewe"
                                                    onClick={() => setLeftChecked(!leftChecked)}
                                                />
                                                <FormInput
                                                    name="right"
                                                    defaultChecked="true"
                                                    type="checkbox"
                                                    containerClass={'form-check-inline m-checkbox col-md-5'}
                                                    label="Prawe"
                                                    onClick={() => setRightChecked(!rightChecked)}
                                                />

                                                {error ? (
                                                    <p className="text-danger mb-3">{error}</p>
                                                ) : <p></p>}
                                                <Row>
                                                    <Col md={6}>
                                                        Value: {selectedValLeft ? <span>{selectedValLeft}</span> : null}
                                                        <FormInput
                                                            type={"range"}
                                                            min={60}
                                                            max={100}
                                                            step={1}
                                                            className={"form-range border-0"}
                                                            onChange={(value) => setSelectedValLeft(value.target.value)}
                                                            disabled={!leftChecked}
                                                        />

                                                        <div className="">
                                                        <Button onClick={next} variant="success"
                                                                className="btn btn-success p-0 px-1"><i
                                                            className="uil-plus font-size-20px"/></Button>
                                                        </div>
                                                        <div className="bg-light rounded my-3">
                                                            <div className="text-center">234</div>
                                                            <div className="text-center">234</div>
                                                        </div>
                                                    </Col>
                                                    <Col md={6}>
                                                        Value: {selectedValRight ?
                                                        <span>{selectedValRight}</span> : null}
                                                        <FormInput
                                                            type={"range"}
                                                            min={60}
                                                            max={100}
                                                            step={1}
                                                            className={"form-range border-0"}
                                                            onChange={(value) => setSelectedValRight(value.target.value)}
                                                            disabled={!rightChecked}
                                                        />
                                                        <div className="">
                                                        <Button onClick={console.log(3)} variant="success"
                                                                className="btn btn-success p-0 px-1"><i
                                                            className="uil-plus font-size-20px"/></Button>
                                                        </div>
                                                        <div className="bg-light rounded my-3">
                                                            <div className="text-center">234</div>
                                                            <div className="text-center">234</div>
                                                        </div>
                                                    </Col>
                                                </Row>


                                                <ul className="list-inline wizard mb-1 mt-2">
                                                    <li className="previous list-inline-item">
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

export default AddProduct;
