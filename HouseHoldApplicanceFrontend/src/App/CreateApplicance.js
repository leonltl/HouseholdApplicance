import React, { useState } from 'react';
import { Form, Header, Button, Icon, Message } from 'semantic-ui-react';
import { DateInput } from 'semantic-ui-calendar-react';
import { Link  } from 'react-router-dom';
import axios from 'axios';
import { StatusOptions } from './Status'


const CreateApplicance = () => {
    const [appliance, setApplicance] = useState({
        serialNum: '',
        brand: '',
        model: '',
        status: '',
        dateBought: ''
    });

    const [isSuccess, setSuccess] = useState(false)
    const [isFail, setFail] = useState(false)

    const handleDateChange = (event, {name, value}) => {
        setApplicance({
            serialNum: appliance.serialNum,
            brand: appliance.brand,
            model: appliance.model,
            status: appliance.status,
            dateBought: value
        })    
    }

    const handleInputChange = (event, {name, value}) => {

        if (name === 'serialNum') {
            setApplicance({
                serialNum: value,
                brand: appliance.brand,
                model: appliance.model,
                status: appliance.status,
                dateBought: appliance.dateBought
            }) 
        }
        else if (name === 'brand') {
            setApplicance({
                serialNum: appliance.serialNum,
                brand: value,
                model: appliance.model,
                status: appliance.status,
                dateBought: appliance.dateBought
            }) 
        }
        else if (name === 'model') {
            setApplicance({
                serialNum: appliance.serialNum,
                brand: appliance.brand,
                model: value,
                status: appliance.status,
                dateBought: appliance.dateBought
            }) 
        }
    }

    const handleSelectionChange = (event, {name, value}) => {
        setApplicance({
            serialNum: appliance.serialNum,
            brand: appliance.brand,
            model: appliance.model,
            status: value,
            dateBought: appliance.dateBought
        }) 
    }

    const handleAdd = (event) => {
        axios.post(`https://desolate-depths-37321.herokuapp.com/api/addApplicance`, 
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
            console.log(res);
        }).catch(error => {
            setFail(true)
            setSuccess(false)
        });

    }

    return (
        <React.Fragment>
            <Header>Add Household Applicance</Header>
            {isSuccess ? 
                <Message
                    success
                    header='Household applicance has been added successful'
                /> : ''
            }
            {isFail ?
            <Message
                error
                header='Existed household applicance record found and is not added in.'
            /> : ''
            }
            <Form>
                <Form.Group widths='equal'>
                    <Form.Input fluid label='Serial Number' 
                        name='serialNum' 
                        placeholder='Serial Number' 
                        id='serialNum' required 
                        value={appliance.serialNum}
                        onChange={handleInputChange}
                        />
                    <Form.Input fluid label='Brand' 
                    placeholder='Brand'
                     name='brand' 
                     id='brand' required 
                     onChange={handleInputChange}
                     value={appliance.brand}/>
                </Form.Group>
                <Form.Group widths='equal'>
                    <Form.Input fluid label='Model' 
                    placeholder='Model' 
                    name='model' id='model' 
                    onChange={handleInputChange}
                    value={appliance.model}
                    required />
                    <Form.Dropdown 
                        label="Select Status"
                        placeholder='Select Status'
                        fluid
                        selection
                        required
                        options={StatusOptions}
                        onChange={handleSelectionChange}
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
                
                <Button onClick={handleAdd} primary>Add</Button>
                <Button><Link to='/'>Cancel</Link></Button>
            </Form>
        </React.Fragment>
      
    );
}


export default CreateApplicance