function loadPage(page, element) {


    const menuItems = document.querySelectorAll(".nav-link");

    // Remove active class from all
    menuItems.forEach(item => item.classList.remove("active"));

    // Add active class to clicked item
    if (element) {
        element.classList.add("active");
    }

    if (page === "home") {
        content.innerHTML = `
            <h2>Home</h2>
            <p>Welcome to modern banking experience.</p>
        `;
    }


    else if (page === "services") {
        content.innerHTML = `
        <h2>Loan Services</h2>

        <div class="loan-card">
            <p><i>Loan Application & EMI Calculator</i></p>

            <form onsubmit="return false;">
                <label>Applicant Name</label>
               
                <input type="text" id="aName">
                <br>
                <label>Type of Loan</label>
                <select id="lType" onchange="setInterest()">
                    <option value="choose">Choose Loan Type</option>
                    <option value="home">Home Loan</option>
                    <option value="car">Car Loan</option>
                    <option value="personal">Personal Loan</option>
                </select>
                <br>
                <label>Interest Rate (%)</label>
                <input type="text" id="iRate" disabled>
                <br>
                <label>Amount</label>
                <input type="text" id="amount" onchange="validInput()">
                <br>
                <label>Duration (Years)</label>
                <input type="number" id="duration" onchange="validInput()">
                <br>
                <button class="primary-btn" onclick="calculateEMI()">Calculate EMI</button>

                <div id="result" class="emi-result"></div>
            </form>
        </div>
    `;
    }


    else if (page === "about") {
        content.innerHTML = `
            <h2>About Us</h2>
            <p>We are committed to innovation & trust.</p>
        `;
    }
    else if (page === "netbanking") {
        content.innerHTML = `
        <div class="card shadow-sm p-4 col-md-6">
            <h4 class="mb-3 text-success">Net Banking</h4>

            <div id="loginForm">
                <input class="form-control mb-3" placeholder="Customer ID">
                <input type="password" class="form-control mb-3" placeholder="Password">
                <button class="btn btn-success w-100">Login</button>

                <p class="mt-3 text-center">
                    Don't have an account?
                    <a href="#" onclick="showSignup()">Sign Up</a>
                </p>
            </div>

            <div id="signupForm" style="display:none;">
                <input class="form-control mb-3" placeholder="Full Name">
                <input type="email" class="form-control mb-3" placeholder="Email">
                <input type="password" class="form-control mb-3" placeholder="Create Password">
                <button class="btn btn-success w-100">Sign Up</button>

                <p class="mt-3 text-center">
                    Already have an account?
                    <a href="#" onclick="showLogin()">Login</a>
                </p>
            </div>
        </div>
    `;
    }
}

function validInput() {

    let loanType = document.getElementById("lType");
    let amount = document.getElementById("amount");
    let duration = document.getElementById("duration");

    let amt = parseFloat(amount.value);
    let dur = parseInt(duration.value);

    let isValid = true;

    amount.style.border = "1px solid #ccc";
    duration.style.border = "1px solid #ccc";

    if (loanType.value === "choose") {
        alert("Please select loan type!");
        return false;
    }

    if (isNaN(amt) || isNaN(dur)) return false;

    if (loanType.value === "home") {
        if (amt < 500000) {
            amount.style.border = "2px solid red";
            isValid = false;
        }
        if (dur > 30) {
            duration.style.border = "2px solid red";
            isValid = false;
        }
    }
    else if (loanType.value === "car") {
        if (amt < 100000) {
            amount.style.border = "2px solid red";
            isValid = false;
        }
        if (dur > 7) {
            duration.style.border = "2px solid red";
            isValid = false;
        }
    }
    else if (loanType.value === "personal") {
        if (amt < 10000) {
            amount.style.border = "2px solid red";
            isValid = false;
        }
        if (dur > 5) {
            duration.style.border = "2px solid red";
            isValid = false;
        }
    }

    return isValid;
}
function showSignup() {
    document.getElementById("loginForm").style.display = "none";
    document.getElementById("signupForm").style.display = "block";
}

function showLogin() {
    document.getElementById("signupForm").style.display = "none";
    document.getElementById("loginForm").style.display = "block";
}
function calculateEMI() {

    if (!validInput()) {
        alert("Invalid Input! Please check highlighted fields.");
        return;
    }

    let amount = document.getElementById("amount");
    let interest = document.getElementById("iRate");
    let duration = document.getElementById("duration");
    let res = document.getElementById("result");

    let p = parseFloat(amount.value);
    let r = parseFloat(interest.value) / 12 / 100;
    let n = parseInt(duration.value) * 12;

    let emi = (p * r * Math.pow(1 + r, n)) /
        (Math.pow(1 + r, n) - 1);

    res.innerHTML = `<strong>Your EMI is:</strong> ₹ ${emi.toFixed(2)}`;
}
function setInterest() {
    const loanTypeElement = document.getElementById("lType");
    const interestElement = document.getElementById("iRate");

    // Loan interest mapping
    const interestRates = {
        home: 9,
        car: 12,
        personal: 15
    };

    const selectedLoan = loanTypeElement.value;
    interestElement.value = interestRates[selectedLoan] || "";
}