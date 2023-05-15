// @flow
import React from 'react';
import {Link} from 'react-router-dom';
import {Button, Card, Col, Modal, Row} from 'react-bootstrap';
import classNames from 'classnames';

// components
import PageTitle from '../../../components/PageTitle';
import Table from '../../../components/Table';
import {getAllVariants, getJwtToken} from "../../../utils/ApiCalls";


// dummy data
const ConfirmBanner = ({
                           isOpen,
                           onClose,
                           onConfirm
                       }) => {
    return (
        <Modal className="d-flex align-items-center justify-content-center" show={isOpen} onHide={onClose}
               backdrop="static" keyboard={false}>
            <Modal.Header className="pb-2 px-4 border-bottom-0">
                <Modal.Title id="modal-title" className="text-center">
                    <h5> {'Na pewno chcesz usunąć?'} </h5>
                </Modal.Title>
            </Modal.Header>
            <Modal.Body className="px-4 pb-4 pt-0">
                <Row>
                    <Col className="text-center">
                        <Button onClick={onConfirm} className="btn btn-danger me-1">
                            Usuń
                        </Button>
                        <Button className="btn btn-light" onClick={onClose}>
                            Anuluj
                        </Button>
                    </Col>
                </Row>
            </Modal.Body>
        </Modal>
    );
};


/* product column render */
const ProductColumn = ({row}) => {


    return (
        <>
            <img
                src={"data:image/webp;base64," + row.original.image}
                alt={row.original.name}
                title={row.original.name}
                className="rounded me-3"
                height="48"
            />
            <p className="m-0 d-inline-block align-middle font-16">
                <Link to="/apps/ecommerce/details" className="text-body">
                    {row.original.name}
                </Link>
            </p>
        </>
    );
};

/* status column render */
const StatusColumn = ({row}) => {
    return (
        <>
            <span
                className={classNames('badge', {
                    'bg-success': row.original.status,
                    'bg-danger': !row.original.status,
                })}>
                {row.original.status ? 'Dostępny' : 'Niedostępny'}
            </span>
        </>
    );
};

// get pagelist to display
const sizePerPageList = [
    {
        text: '5',
        value: 5,
    },
    {
        text: '10',
        value: 10,
    },
    {
        text: '20',
        value: 20,
    },
    {
        text: '50',
        value: 50,
    },
];


class Products extends React.Component {

    _isMounted = false;


    constructor() {
        super();
        this.state = {
            isVisible: false,
            variants: [],
            columns: [
                {
                    Header: 'Produkt',
                    accessor: 'name',
                    sort: true,
                    Cell: ProductColumn,
                },
                {
                    Header: 'Kolor',
                    accessor: 'color',
                    sort: true,
                },
                {
                    Header: 'Strona',
                    accessor: 'side',
                    sort: true,
                },
                {
                    Header: 'Wzór',
                    accessor: 'pattern',
                    sort: true,
                },
                {
                    Header: 'Data dodania',
                    accessor: 'added_date',
                    sort: true,
                },

                {
                    Header: 'Cena',
                    accessor: 'price',
                    sort: true,
                },
                {
                    Header: 'Ilość',
                    accessor: 'quantity',
                    sort: true,
                },
                {
                    Header: 'Status',
                    accessor: 'status',
                    sort: true,
                    Cell: StatusColumn,
                },
                {
                    accessor: 'action',
                    sort: false,
                    classes: 'table-action',
                    Cell: this.ActionColumn,
                },
            ]
        };
    }


    componentDidMount() {
        this._isMounted = true;
        this.fetchData();
    }

    componentWillUnmount() {
        this._isMounted = false;
    }


    ActionColumn = ({row}) => {


        const handleDelete = () => {
            const headers = new Headers();
            headers.append('Authorization', `Bearer ${getJwtToken()}`);


            const request = new Request(process.env.REACT_APP_API_URL + "variants/" + row.original.id, {
                method: 'DELETE',
                credentials: "include",
                headers: headers,
            });

            fetch(request)
                .then(() => {
                    this.fetchData();
                    this.render();
                })
                .catch(error => {
                    console.log(error)
                });

        };


        const handleConfirm = () => {
            this.state.isVisible = false;
            handleDelete();
        };


        const handleCancel = () => {
            console.log(this.state.isVisible)
            this.state.isVisible = false;
            this.fetchData();
            console.log(this.state.isVisible)
        };

        const onDelete = () => {
            this.state.isVisible = true;
        }

        return (
            <>
                <Link to="#" className="action-icon">
                    {' '}
                    <i className="mdi mdi-eye"></i>
                </Link>
                <Link to="#" className="action-icon">
                    {' '}
                    <i className="mdi mdi-square-edit-outline"></i>
                </Link>
                <Link to="#" className="action-icon" onClick={onDelete}>
                    {' '}
                    <i className="mdi mdi-delete"></i>
                </Link>
                {this.state.isVisible ? (
                    <ConfirmBanner
                        isOpen={this.state.isVisible}
                        onClose={handleCancel}
                        onConfirm={handleConfirm}
                    />
                ) : null}
            </>
        );
    };

    async fetchData() {
        if (this._isMounted) {
            getAllVariants().then(result => this.setState({variants: result}));
        }
    }


    render() {
        return (
            <>
                <PageTitle
                    breadCrumbItems={[
                        {label: 'eCommerce', path: '/apps/ecommerce/products'},
                        {label: 'Products', path: '/apps/ecommerce/products', active: true},
                    ]}
                    title={'Products'}
                />

                <Row>
                    <Col xs={12}>
                        <Card>
                            <Card.Body>
                                <Row className="mb-2">
                                    <Col sm={5}>
                                        <Link to="#" className="btn btn-danger mb-2">
                                            <i className="mdi mdi-plus-circle me-2"></i> Add Products
                                        </Link>
                                    </Col>

                                    <Col sm={7}>
                                        <div className="text-sm-end">
                                            <Button variant="success" className="mb-2 me-1">
                                                <i className="mdi mdi-cog-outline"></i>
                                            </Button>

                                            <Button variant="light" className="mb-2 me-1">
                                                Import
                                            </Button>

                                            <Button variant="light" className="mb-2">
                                                Export
                                            </Button>
                                        </div>
                                    </Col>
                                </Row>

                                <Table
                                    columns={this.state.columns}
                                    data={this.state.variants}
                                    pageSize={5}
                                    sizePerPageList={sizePerPageList}
                                    isSortable={true}
                                    pagination={true}
                                    isSelectable={true}
                                    isSearchable={true}
                                    theadClass="table-light"
                                    searchBoxClass="mb-2"
                                />
                            </Card.Body>
                        </Card>
                    </Col>
                </Row>
            </>
        );
    }
}


// main component


export default Products;

