//"use strict";
function test() {
    let x = 5;
    alert(x);
}


function getX() {
    const xInput = document.querySelector('input[name="x"]:checked');
    return xInput ? parseFloat(xInput.value) : null;
}

function getRawY() {
    return document.getElementById("y").value.trim();
}

function getY() {
    const yVal = document.getElementById("y").value.trim();
    if (!/^[-+]?\d*(\.\d+)?$/.test(yVal) || yVal === "" || yVal === "." || yVal === "-" || yVal === "+") {
        return null;
    }
    const yNum = parseFloat(yVal);
    return isNaN(yNum) ? null : yNum;
}

function getR() {
    const rInput = document.querySelector('input[name="radius"]:checked');
    return rInput ? parseFloat(rInput.value) : null;
}

function validateUserInput(x, y, r) {
    return (
        x !== null &&
        r !== null &&
        y !== null &&
        y > -5 &&
        y < 3
    );
}

function addTableRow(x, y, r, hit, date, execTime) {
    const table = document.querySelector(".table-check");
    const row = table.insertRow(-1);
    row.insertCell(0).textContent = x;
    row.insertCell(1).textContent = y;
    row.insertCell(2).textContent = r;
    row.insertCell(3).textContent = hit ? "Есть пробитие" : "Броня не пробита";
    row.insertCell(4).textContent = date;
    row.insertCell(5).textContent = execTime + " ms";
}

function movePoint(x, y, r) {
    const point = document.getElementById("point");
    const scale = 120 / r;
    const cx = 300 + x * scale;
    const cy = 300 - y * scale;

    point.setAttribute("cx", cx);
    point.setAttribute("cy", cy);
    point.setAttribute("visibility", "visible");
}

// eslint-disable-next-line no-unused-vars
async function submitToBackend() {
    const x = getX();
    const y = getY();
    const rawY = getRawY();
    const r = getR();

    if (!validateUserInput(x, y, r)) {
        alert("Введите корректные значения:\nY должно быть числом от -5 до 3.");
        return;
    }

    const data = { x, y, r };

    try {
        const response = await fetch("/fcgi/", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(data)
        });

        if (!response.ok) throw new Error(`Ошибка сервера: ${response.status}`);

        const raw = await response.text();
        console.log("Сырой ответ сервера:", raw);
        const parsed = JSON.parse(raw);
        const body = parsed.body;
        const execTime = parsed.executionTime;
        movePoint(x, y, r)
        addTableRow(
            body.x,
            rawY,
            body.r,
            body.hit,
            body.date,
            execTime
        );
        
    } catch (err) {
        console.error("Запрос не выполнен:", err);
        alert("Произошла ошибка при отправке данных на сервер.");
    }
}
