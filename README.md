# addingApi
Simple api for adding two values and seeing a list of all calculations performed

# Endpoints
## add
- endpoint adds two numbers and returns an object with both of the numbers and the result.
RequestParameters
- number1 - the first number to be added.
- number2 - the second number to be added.
- Response example
> {
    "firstNumber": 3,
    "secondNumber": 3,
    "result": 6
}

## records
Endpoint related to the recorded results of previous calculations.
### Get
RequestParameters
- number(optional) a number used in sorting the result. The response has only records that have the number in the as one of the numbers or as the result.
- ascend - boolean parameter if true then the response list is ordered in a ascending order. If false then the order will be descending.
- response example with number = 3 and ascend = true.
> [
    {
        "firstNumber": 1,
        "secondNumber": 2,
        "result": 3
    },
    {
        "firstNumber": 3,
        "secondNumber": 3,
        "result": 6
    }
]

### delete
This endpoint is for cleaning up the records list and is mainly used by the integration tests.
