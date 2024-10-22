function init() {
    renderCustomers();
}

async function getCustomers() {
    let url = URL_SERVER + 'customer';

    let config = {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
          'Authorization' : sessionStorage.token
        }
    }

    let response = await fetch(url, config);
    let json = await response.json();
    return json;
    // return [
    //     {
    //         "id": 1,
    //         "firstName": "Bautista",
    //         "lastName": "Exposito",
    //         "email": "bautistaexposito@exposito.com",
    //         "address": "Blandengang 55"
    //     },
    //     {
    //         "id": 2,
    //         "firstName": "Pelao",
    //         "lastName": "Khe",
    //         "email": "pelaokhe@keloke.com.ar",
    //         "address": "Somewhere 3221"
    //     }
    // ];
}

async function renderCustomers() {
    let customers = await getCustomers();
    let html = '';
    for (let customer of customers) {
        html += getHtmlRowCustomer(customer);
    }

    let tbody = document.getElementById('tbody-customers');
    tbody.innerHTML = html;
}

async function onClickEdit(id) {
    window.location.href = 'modify-customer.html?id='+id;
}

async function onClickRemove(id) {
    let response = confirm("Do you want to remove this customer?");
    if (!response) {
        return;
    }

    let url = URL_SERVER + 'customer/' +id;
    let config = {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            'Authorization' : sessionStorage.token
          }
    };
    
    await fetch(url, config);
    renderCustomers();
}

function getHtmlRowCustomer(customer) {
    return `
    <tr>
        <td>${customer.id}</td>
        <td>${customer.firstName} ${customer.lastName}</td>
        <td>${customer.address}</td>
        <td>${customer.email}</td>
        <td>
            <a href="#" onclick="onClickEdit(${customer.id})" class="btn btn-primary btn-circle btn-sm">
                <i class="fas fa-edit"></i>
            </a>

            <a href="#" onclick="onClickRemove(${customer.id})" class="btn btn-danger btn-circle btn-sm">
                <i class="fas fa-trash"></i>
            </a>
        </td>
    </tr>
    `;
}

window.onload = init;