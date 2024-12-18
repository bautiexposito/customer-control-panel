async function loadCustomer() {
    if (isNew()) {
        return;
    }

    let id = getCustomerId();
    let customer = await getCustomerById(id);

    document.getElementById('txtFirstname').value = customer.firstName;
    document.getElementById('txtLastname').value = customer.lastName;
    document.getElementById('txtEmail').value = customer.email;
    document.getElementById('txtAddress').value = customer.address;
}

function getCustomerId() {
    let auxSplit = window.location.href.split('id=');
    let id = auxSplit[1];
    return id;
}

function isNew() {
    let hasIdInUrl = window.location.href.includes('id=');
    return !hasIdInUrl;
}

async function getCustomerById(id) {
    let url = URL_SERVER + 'customer/'+id;
    let config = {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': sessionStorage.token
        }
    };
    let response = await fetch(url,config);
    let json = await response.json();
    return json;
}

function clickCreate () {
    let firstname = document.getElementById('txtFirstname').value;
    let lastname = document.getElementById('txtLastname').value;
    let email = document.getElementById('txtEmail').value;
    let address = document.getElementById('txtAddress').value;

    let customer = {
        "firstName": firstname,
        "lastName": lastname,
        "email": email,
        "address": address
    };
    save(customer);
}

async function save(customer) {
    let url = URL_SERVER + 'customer';
    let methodType = isNew() ? 'POST' : 'PUT';

    if (!isNew()) {
        url += '/' + getCustomerId();
    }

    let config = {
        "method": methodType,
        "body": JSON.stringify(customer),
        "headers": {
            'Content-Type': 'application/json',
            'Authorization' : sessionStorage.token
        }
    };
    
    await fetch(url, config);
    alert('El cliente se guardo correctamente');
    window.location.href = 'customers.html';
}

window.onload = loadCustomer;
