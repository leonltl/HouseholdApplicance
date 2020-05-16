import React, { useState, useEffect } from 'react';
import { Header, Button, Message, Grid, Segment } from 'semantic-ui-react';
import { Link  } from 'react-router-dom';
import axios from 'axios';

const DeleteApplicance = ({ match }) => {
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


    const handleDelete = (event) => {
        var id = appliance.id;
        if (id > 0) {
            axios.delete(`https://desolate-depths-37321.herokuapp.com/api/deleteApplicance/` + id, 
                JSON.stringify(appliance), {headers: {
                    "Content-Type": "application/json"}
                })
            .then(res => {
                if (res.status === 200 || res.status === 201) {
                    setSuccess(true)
                    setFail(false)
                    setApplicance({
                        id: 0,
                        serialNum: '',
                        brand:  '',
                        model:  '',
                        status:  '',
                        dateBought: ''
                    }) 
                }
                else if (res.status >= 400) {
                    setFail(true)
                    setSuccess(false)
                }
            })    
        }
    }

    return (
        <React.Fragment>
            <Header>Delete Household Applicance</Header>
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
            
            <Grid columns='equal'>
                <Grid.Row>
                    <Grid.Column width={4}>
                        <Segment>
                            <label>Serial Number</label>
                            <p><strong>{appliance.serialNum}</strong></p>
                        </Segment>
                        
                    </Grid.Column>
                    <Grid.Column width={4}>
                        <Segment>
                            <label>Brand</label>
                            <p><strong>{appliance.brand}</strong></p>
                        </Segment>
                    </Grid.Column>
                    <Grid.Column width={4}>
                        <Segment>
                            <label>Model</label>
                            <p><strong>{appliance.model}</strong></p>
                        </Segment>
                    </Grid.Column>
                </Grid.Row>
                <Grid.Row>
                    <Grid.Column width={4}>
                        <Segment>
                            <label>Status</label>
                            <p><strong>{appliance.status}</strong></p>
                        </Segment>
                    </Grid.Column>
                    <Grid.Column width={4}>
                        <Segment>
                            <label>Date Bought</label>
                            <p><strong>{appliance.dateBought}</strong></p>
                        </Segment>
                    </Grid.Column>
                </Grid.Row>
                <Grid.Row>
                    <Grid.Column width={4}>
                        <Button onClick={handleDelete} primary>Delete</Button>
                        <Link to='/'><Button>Cancel</Button></Link>
                    </Grid.Column>
                </Grid.Row>
            </Grid>
        </React.Fragment>
      
    );
}


export default DeleteApplicance