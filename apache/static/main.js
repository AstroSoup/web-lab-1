"use strict";

function validateUserInput() {}

function submitToBackend() {
    let url = "/fcgi-bin/";
    const xhr = new XMLHttpRequest();
    xhr.withCredentials = true;
    xhr.open("GET", url+(query.length ? '?' + query.join('&') : ''));

    xhr.onreadystatechange = () => {
        if (xhr.readyState == 4) {
            alert(xhr.responseText)
        }
    }
}

function addTableRow() {}
