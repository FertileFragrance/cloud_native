*** Settings ***
Library           requests

*** Test Cases ***
case 1
    ${res}    requests.get    http://172.29.4.36:30006/hello
    should contain    ${res.text}    hello