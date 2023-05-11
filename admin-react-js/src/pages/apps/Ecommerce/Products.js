// @flow
import React, {useEffect, useState} from 'react';
import {Link} from 'react-router-dom';
import {Button, Card, Col, Row} from 'react-bootstrap';
import classNames from 'classnames';

// components
import PageTitle from '../../../components/PageTitle';
import Table from '../../../components/Table';

// dummy data

/* product column render */
const ProductColumn = ({ row }) => {



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
const StatusColumn = ({ row }) => {
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

/* action column render */
const ActionColumn = ({ row }) => {
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
            <Link to="#" className="action-icon">
                {' '}
                <i className="mdi mdi-delete"></i>
            </Link>
        </>
    );
};

// get all columns
const columns = [
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
        Cell: ActionColumn,
    },
];

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

// main component
const Products = (): React$Element<React$FragmentType> => {

    const [variants, setVariants] = useState([]);

    useEffect(() => {
        async function fetchData() {
            try {
                // make a GET request to the /variants endpoint
                const variantsResponse = await fetch(process.env.REACT_APP_API_URL + 'variants');
                const variantsJson = await variantsResponse.json();

                // map the variants array to an array of objects with the desired fields
                const variantsWithoutImages = variantsJson.map(variant => ({
                    id: variant.variantId,
                    name: variant.productName,
                    color: variant.colorName,
                    side: variant.side,
                    pattern: variant.pattern,
                    image: null,
                    added_date: variant.addedDate,
                    price: variant.price,
                    quantity: variant.quantity,
                    status: variant.enabled
                }));

                // construct a comma-separated list of product IDs
                const variantIds = variantsWithoutImages.map(variant => variant.id).join("&variantId=");

                // make a GET request to the /images endpoint with the list of product IDs
                const imagesURL = process.env.REACT_APP_API_URL + `files/ByVariantIds?variantId=${variantIds}`;
                const imagesResponse = await fetch(imagesURL);
                const images = await imagesResponse.json();

                // update the variants array with the image data
                const variantsWithImages = variantsWithoutImages.map(variant => {
                    const image = images.find(image => image.productId === variant.id);
                    if (image) {
                        return {
                            ...variant,
                            image: image.image
                        };
                    } else {
                        return variant;
                    }
                });
                // update the state with the updated variants array
                await setVariants(variantsWithImages);
            } catch (error) {
                console.error(error);
            }
        }

        fetchData();
    }, []);


    return (
        <>
            <PageTitle
                breadCrumbItems={[
                    { label: 'eCommerce', path: '/apps/ecommerce/products' },
                    { label: 'Products', path: '/apps/ecommerce/products', active: true },
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
                                columns={columns}
                                data={variants}
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
};

export default Products;
