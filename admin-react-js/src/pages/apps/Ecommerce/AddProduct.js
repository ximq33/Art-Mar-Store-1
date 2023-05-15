import {yupResolver} from "@hookform/resolvers/yup";
import * as yup from "yup";
import {Button, Card, Col, Form, ProgressBar, Row} from "react-bootstrap";
import {Step, Steps, Wizard} from "react-albus";
import {FormInput, VerticalForm} from "../../../components";
import React from "react";
import {WizardWithFormValidation} from "../../forms/Wizard";



const AddProduct = (): React$Element<React$FragmentType> => {
    /*
     * form validation schema
     *
     *
     */

    return (
        <>
        <WizardWithFormValidation/>
        </>
    )

    //
    // const validationSchema = yupResolver(
    //     yup.object().shape({
    //         email: yup.string().required('Please enter Email address'),
    //         password: yup.string().required('Please enter Password'),
    //         checkbox: yup.bool().oneOf([true]),
    //     })
    // );
    // const validationSchema2 = yupResolver(
    //     yup.object().shape({
    //         firstname: yup.string().required('Please enter First Name'),
    //         lastname: yup.string().required('Please enter Last Name'),
    //         checkbox: yup.bool().oneOf([true]),
    //     })
    // );
    //
    // return (
    //     <div style={{
    //         display: 'flex',
    //         justifyContent: 'center',
    //         alignItems: 'center',
    //         height: '100vh'
    //     }}>
    //         <div style={{ width: '50%', maxWidth: '800px' }}>
    //             <Card>
    //                 <Card.Body>
    //                     <h4 className="header-title mb-3">Dodaj nowy produkt</h4>
    //
    //                     <Wizard
    //                         render={({step, steps}) => (
    //                             <>
    //                                 <ProgressBar
    //                                     animated
    //                                     striped
    //                                     variant="success"
    //                                     now={((steps.indexOf(step) + 1) / steps.length) * 100}
    //                                     className="mb-3 progress-sm"
    //                                 />
    //
    //                                 <Steps>
    //                                     <Step
    //                                         id="login"
    //                                         render={({next}) => (
    //                                             <VerticalForm onSubmit={(event, values) => next()}
    //                                                           resolver={validationSchema}>
    //                                                 <FormInput
    //                                                     label="Nazwa produktu"
    //                                                     type="text"
    //                                                     name="name"
    //                                                     containerClass={'mb-3'}
    //                                                 />
    //                                                 <FormInput
    //                                                     label="Producent"
    //                                                     type="text"
    //                                                     name="producer"
    //                                                     containerClass={'mb-3'}
    //                                                 />
    //                                                 <FormInput
    //                                                     label="Opis produktu"
    //                                                     type="textarea"
    //                                                     name="description"
    //                                                     containerClass={'mb-3'}
    //                                                 />
    //
    //                                                 <FormInput label="Remember me" type="checkbox" name="checkbox"/>
    //
    //                                                 <ul className="list-inline wizard mb-0">
    //                                                     <li className="next list-inline-item float-end">
    //                                                         <Button variant="success" type="submit">
    //                                                             Next
    //                                                         </Button>
    //                                                     </li>
    //                                                 </ul>
    //                                             </VerticalForm>
    //                                         )}
    //                                     />
    //                                     <Step
    //                                         id="gandalf"
    //                                         render={({next, previous}) => (
    //                                             <VerticalForm onSubmit={(event, values) => next()}
    //                                                           resolver={validationSchema2}>
    //                                                 <FormInput
    //                                                     label="First Name"
    //                                                     type="text"
    //                                                     name="firstname"
    //                                                     containerClass={'mb-3'}
    //                                                 />
    //                                                 <FormInput
    //                                                     label="Last Name"
    //                                                     type="text"
    //                                                     name="lastname"
    //                                                     containerClass={'mb-3'}
    //                                                 />
    //
    //                                                 <FormInput
    //                                                     label="Agree to terms and conditions"
    //                                                     type="checkbox"
    //                                                     name="checkbox"
    //                                                     containerClass={'mb-3'}
    //                                                 />
    //
    //                                                 <ul className="list-inline wizard mb-0">
    //                                                     <li className="previous list-inline-item">
    //                                                         <Button onClick={previous} variant="info">
    //                                                             Previous
    //                                                         </Button>
    //                                                     </li>
    //                                                     <li className="next list-inline-item float-end">
    //                                                         <Button variant="success" type="submit">
    //                                                             Next
    //                                                         </Button>
    //                                                     </li>
    //                                                 </ul>
    //                                             </VerticalForm>
    //                                         )}
    //                                     />
    //                                     <Step
    //                                         id="dumbledore"
    //                                         render={({previous}) => (
    //                                             <Row>
    //                                                 <Col sm={12}>
    //                                                     <div className="text-center">
    //                                                         <h2 className="mt-0">
    //                                                             <i className="mdi mdi-check-all"></i>
    //                                                         </h2>
    //                                                         <h3 className="mt-0">Thank you !</h3>
    //
    //                                                         <p className="w-75 mb-2 mx-auto">
    //                                                             Quisque nec turpis at urna dictum luctus. Suspendisse
    //                                                             convallis
    //                                                             dignissim eros at volutpat. In egestas mattis dui.
    //                                                             Aliquam
    //                                                             mattis dictum aliquet.
    //                                                         </p>
    //
    //                                                         <div className="mb-3">
    //                                                             <Form.Check type="checkbox" className="d-inline-block">
    //                                                                 <Form.Check.Input type="checkbox"/>{' '}
    //                                                                 <Form.Check.Label>
    //                                                                     I agree with the Terms and Conditions
    //                                                                 </Form.Check.Label>
    //                                                             </Form.Check>
    //                                                         </div>
    //                                                     </div>
    //                                                 </Col>
    //
    //                                                 <Col sm={12}>
    //                                                     <ul className="list-inline wizard mb-0">
    //                                                         <li className="previous list-inline-item">
    //                                                             <Button onClick={previous} variant="info">
    //                                                                 Previous
    //                                                             </Button>
    //                                                         </li>
    //
    //                                                         <li className="next list-inline-item float-end">
    //                                                             <Button variant="success">Submit</Button>
    //                                                         </li>
    //                                                     </ul>
    //                                                 </Col>
    //                                             </Row>
    //                                         )}
    //                                     />
    //                                 </Steps>
    //                             </>
    //                         )}
    //                     />
    //                 </Card.Body>
    //             </Card>
    //         </div>
    //     </div>
    //
    // );
};

export default AddProduct;