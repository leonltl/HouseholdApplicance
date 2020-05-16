import React, { useState, useEffect } from 'react';
import { Table, Button, Header, Grid, Form } from 'semantic-ui-react';
import { Link  } from 'react-router-dom';
import { DateInput } from 'semantic-ui-calendar-react';
import axios from 'axios';
import { StatusOptions } from './Status'

const ManageApplicance = () => {
    const [filter, setFilter] = useState({
        serialNum: '',
        model: '',
        brand: '',
        status: '',
        dateBought: ''
    });
    const [applicances, setApplicances] = useState([]);

    useEffect(() => {
        axios.get(`https://desolate-depths-37321.herokuapp.com/api/applicances`).then(response => {
            var applicancesRes = response.data;
            setApplicances(applicancesRes);
        });  
         
    }, [])

    const handleFilter = (event, {name, value}) => {
        axios.post(`https://desolate-depths-37321.herokuapp.com/api/searchApplicance`, JSON.stringify(filter), {headers: {
            "Content-Type": "application/json"}
        }).then(res => {
            if (res.status === 200 || res.status === 201) {
                var applicancesRes = res.data;
                setApplicances(applicancesRes);
            }
           
            console.log(res);
        })   
    }

    const onFilterFieldsChange = (event, {name, value}) => {
        if (name === 'serialNum') {
            setFilter({
                serialNum: value,
                brand: filter.brand,
                model: filter.model,
                status: filter.status,
                dateBought: filter.dateBought
            })
        }
        else if (name === 'brand') {
            setFilter({
                serialNum: filter.serialNum,
                brand: value,
                model: filter.model,
                status: filter.status,
                dateBought: filter.dateBought
            })
        }
        else if (name === 'model') {
            setFilter({
                serialNum: filter.serialNum,
                brand: filter.brand,
                model: value,
                status: filter.status,
                dateBought: filter.dateBought
            })
        }
        else if (name === 'status') {
            setFilter({
                serialNum: filter.serialNum,
                brand: filter.brand,
                model: filter.model,
                status: value,
                dateBought: filter.dateBought
            })
        }
    }

    const handleDateChange = (event, {name, value}) => {
        setFilter({
            serialNum: filter.serialNum,
            brand: filter.brand,
            model: filter.model,
            status: filter.status,
            dateBought: value
        }) 
    }

    return (
      <div className="manage-applicance">
        <Header>Manage Household Applicance</Header>
        <Grid>
            <Grid.Row>
                <Grid.Column width={3}>
                    <Form.Input fluid label='Filter By Serial Number' 
                        name='serialNum' 
                        placeholder='Serial Number' 
                        id='serialNum' 
                        onChange={onFilterFieldsChange}
                        value={filter.serialNum}
                    />
                </Grid.Column>
                <Grid.Column width={2}>
                    <Form.Input fluid label='Filter By Brand' 
                        name='brand' 
                        placeholder='Brand' 
                        id='brand' 
                        onChange={onFilterFieldsChange}
                        value={filter.brand}
                    />
                </Grid.Column>
                <Grid.Column width={2}>
                    <Form.Input fluid label='Filter By Model' 
                        name='model' 
                        placeholder='Model' 
                        id='model' 
                        onChange={onFilterFieldsChange}
                        value={filter.model}
                    />
                </Grid.Column>
                <Grid.Column width={2}>
                    <Form.Dropdown
                        label='Filter By Status' 
                        name='status'
                        placeholder='Status'
                        fluid
                        clearable
                        search
                        selection
                        options={StatusOptions}
                        onChange={onFilterFieldsChange}
                        value={filter.status}
                    />
                </Grid.Column>
                <Grid.Column width={3}>
                    <label>Filter By Bought Date</label>
                    <DateInput
                        name="Start Date"
                        value={filter.dateBought}
                        placeholder="Start Date"
                        onChange={handleDateChange}
                        />
                </Grid.Column>
                <Grid.Column width={3}>
                    <div className="field">
                        <label>Filter</label>
                        <div className="ui fluid input">
                            <Button basic color="blue" onClick={handleFilter}>Filter</Button>
                        </div>
                    </div>
                </Grid.Column>
                
             </Grid.Row>
        </Grid>
        <Table celled>
            <Table.Header>
                <Table.Row>
                    <Table.HeaderCell>Serial Number</Table.HeaderCell>
                    <Table.HeaderCell>Brand</Table.HeaderCell>
                    <Table.HeaderCell>Model</Table.HeaderCell>
                    <Table.HeaderCell>Status</Table.HeaderCell>
                    <Table.HeaderCell>Bought Date</Table.HeaderCell>
                    <Table.HeaderCell></Table.HeaderCell>
                </Table.Row>
            </Table.Header>

            <Table.Body>
                {applicances.map(item => (
                    <Table.Row key={item.id}>
                        <Table.Cell>{item.serialNum}</Table.Cell>
                        <Table.Cell>{item.brand}</Table.Cell>
                        <Table.Cell>{item.model}</Table.Cell>
                        <Table.Cell>{item.status}</Table.Cell>
                        <Table.Cell>{item.dateBought}</Table.Cell>
                        <Table.Cell>
                            <Link to={"/update/" + item.id}><Button basic color='green'>Update</Button></Link>
                            {item.status !== 'In used' ? <Link to={{pathname: "/delete/" + item.id}}><Button basic color='red'>Delete</Button></Link> : ''}
                        </Table.Cell>
                    </Table.Row>
                ))}
            </Table.Body>
        </Table>
        <div>
            <p><Link to='/create'><Button basic color="blue">Create Applicance</Button></Link></p>
        </div>
      </div>
    );
}

export default ManageApplicance
