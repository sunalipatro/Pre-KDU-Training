const num1Field = document.getElementById("num1");
const num2Field = document.getElementById("num2");
const add = document.getElementById("add");
const subtract = document.getElementById("subtract");
const multiply = document.getElementById("multiply");
const divide = document.getElementById("divide");
function getNumbers() {
    const num1 = parseFloat(num1Field.value);
    const num2 = parseFloat(num2Field.value);
    if (isNaN(num1) || isNaN(num2)) {
        alert("Please enter valid numbers.");
        return null;
    }
    return {
        num1,
        num2
    };
}
add.addEventListener("click", ()=>{
    const numbers = getNumbers();
    if (numbers) alert(`Result: ${numbers.num1 + numbers.num2}`);
});
subtract.addEventListener("click", ()=>{
    const numbers = getNumbers();
    if (numbers) alert(`Result: ${numbers.num1 - numbers.num2}`);
});
multiply.addEventListener("click", ()=>{
    const numbers = getNumbers();
    if (numbers) alert(`Result: ${numbers.num1 * numbers.num2}`);
});
divide.addEventListener("click", ()=>{
    const numbers = getNumbers();
    if (numbers) {
        if (numbers.num2 === 0) alert("Cannot divide by zero");
        else alert(`Result: ${numbers.num1 / numbers.num2}`);
    }
});

//# sourceMappingURL=index.7cfde5cc.js.map
