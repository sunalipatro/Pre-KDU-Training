let counterValue = 0;
const incre = document.getElementById("increment");
const decr = document.getElementById("decrement");
const value = document.getElementById("counter-value");

incre.addEventListener("click", () => {
  counterValue++;
  value.textContent = counterValue;
});

decr.addEventListener("click", () => {
  counterValue--;
  value.textContent = counterValue;
});
