import React, { useState, useEffect } from 'react';
import { Form, Header, Button, Icon, Message } from 'semantic-ui-react';
import { DateInput } from 'semantic-ui-calendar-react';
import { Link  } from 'react-router-dom';
import axios from 'axios';
import { StatusOptions } from './Status'

const UpdateApplicance = ({ match }) => {
    const [appliance, setApplicance] = useState({
        id: 0,
        serialNum: '',
        brand: '',
        model: '',
        status: '',
        dateBought: ''
    });

    const [isSuccess, setSuccess] = useState(false)
    const [isFail, setFail] = useState(false)
    useEffect(() => {
        if (match.params !== 'undefined') {
            var id = match.params.id;    
            axios.get(`https://desolate-depths-37321.herokuapp.com/api/applicance/` + id).then(response => {
                console.log(response.data);
                setApplicance({
                    id: response.data.id,
                    serialNum: response.data.serialNum,
                    brand:  response.data.brand,
                    model:  response.data.model,
                    status:  response.data.status,
                    dateBought:  response.data.dateBought
                }) 
            }); 
        }
         
         
    }, [match.params])

    const handleDateChange = (event, {name, value}) => {
        setApplicance({
            id: appliance.id,
            serialNum: appliance.serialNum,
            brand: appliance.brand,
            model: appliance.model,
            status: appliance.status,
            dateBought: value
        })    
    }

    const handleFieldsChange = (event, {name, value}) => {

        if (name === 'serialNum') {
            setApplicance({
                id: appliance.id,
                serialNum: value,
                brand: appliance.brand,
                model: appliance.model,
                status: appliance.status,
                dateBought: appliance.dateBought
            }) 
        }
        else if (name === 'brand') {
            setApplicance({
                id: appliance.id,
                serialNum: appliance.serialNum,
                brand: value,
                model: appliance.model,
                status: appliance.status,
                dateBought: appliance.dateBought
            }) 
        }
        else if (name === 'model') {
            setApplicance({
                id: appliance.id,
                serialNum: appliance.serialNum,
                brand: appliance.brand,
                model: value,
                status: appliance.status,
                dateBought: appliance.dateBought
            }) 
        }
        else if (name === 'status') {
            setApplicance({
                id: appliance.id,
                serialNum: appliance.serialNum,
                brand: appliance.brand,
                model: appliance.model,
                status: value,
                dateBought: appliance.dateBought
            })
        }
    }

    const handleUpdate = (event) => {
        var id = appliance.id;
        setApplicance({
            id: appliance.id,
            serialNum: appliance.serialNum,
            brand: appliance.brand,
            model: appliance.model,
            status: appliance.status,
            dateBought: appliance.dateBought
        })  
        axios.put(`https://desolate-depths-37321.herokuapp.com/api/updateApplicance/` + id, 
            JSON.stringify(appliance), {headers: {
                "Content-Type": "application/json"}
            })
        .then(res => {
            if (res.status === 200 || res.status === 201) {
                setSuccess(true)
                setFail(false)
            }
            else if (res.status >= 400) {
                setFail(true)
                setSuccess(false)
            }
        }).catch(error => {
            setFail(true)
            setSuccess(false)
        });  
    }

    return (
        <React.Fragment>
            <Header>Edit Household Applicance</Header>
            {isSuccess ? 
                <Message
                    success
                    header='Household applicance has been updated successful'
                /> : ''
            }
            {isFail ?
            <Message
                error
                header="Existed household applicance record found and won't not updated in."
            /> : ''
            }
            <Form>
                <Form.Group widths='equal'>
                    <Form.Input fluid label='Serial Number' 
                        name='serialNum' 
                        placeholder='Serial Number' 
                        id='serialNum' required 
                        value={appliance.serialNum}
                        onChange={handleFieldsChange}
                        />
                    <Form.Input fluid label='Brand' 
                    placeholder='Brand'
                     name='brand' 
                     id='brand' required 
                     onChange={handleFieldsChange}
                     value={appliance.brand}/>
                </Form.Group>
                <Form.Group widths='equal'>
                    <Form.Input fluid label='Model' 
                    placeholder='Model' 
                    name='model' id='model' 
                    onChange={handleFieldsChange}
                    value={appliance.model}
                    required />
                    <Form.Dropdown 
                        label="Select Status"
                        placeholder='Select Status'
                        name='status'
                        fluid
                        selection
                        required
                        options={StatusOptions}
                        value={appliance.status}
                        onChange={handleFieldsChange}
                    />
                </Form.Group>
                <Form.Field>
                    <label>Bought Date</label>
                    <DateInput
                        name="Start Date"
                        required
                        clearIcon={<Icon name="remove" color="red" />}
                        placeholder="Start Date"
                        value={appliance.dateBought}
                        onChange={handleDateChange}
                        />
                </Form.Field>
                
                <Button onClick={handleUpdate} primary>Update</Button>
                <Link to='/'><Button>Cancel</Button></Link>
            </Form>
        </React.Fragment>
      
    );
}


export default UpdateApplicance