//document.addEventListener("DOMContentLoaded", function () {
//    const formOpenBtn = document.querySelector("#form-open"),
//        home = document.querySelector(".home"),
//        formCloseBtn = document.querySelector(".form_close"),
//        signupBtn = document.querySelector("#signup"),
//        loginBtn = document.querySelector("#login"),
//        registerBtn = document.querySelector("#register-form-open"),
//        pwShowHide = document.querySelectorAll(".pw_hide"),
//        loginForm = document.querySelector(".login_form"),
//        registerForm = document.querySelector(".signUpForm");
//
//    // Popup function for success messages
//    function showPopup(message) {
//        const popup = document.createElement("div");
//        popup.textContent = message;
//        popup.style.position = "fixed";
//        popup.style.top = "50%";
//        popup.style.left = "50%";
//        popup.style.transform = "translate(-50%, -50%)";
//        popup.style.padding = "20px";
//        popup.style.backgroundColor = "#4caf50";
//        popup.style.color = "#fff";
//        popup.style.borderRadius = "10px";
//        popup.style.boxShadow = "0 0 10px rgba(0, 0, 0, 0.5)";
//        popup.style.zIndex = "1000";
//        popup.style.textAlign = "center";
//        popup.style.fontSize = "18px";
//
//        document.body.appendChild(popup);
//
//        setTimeout(() => {
//            popup.remove();
//        }, 3000);
//    }
//
//    // Open login form
//    formOpenBtn?.addEventListener("click", () => {
//        home.classList.add("show");
//        loginForm.classList.remove("hidden");
//        registerForm.classList.add("hidden");
//    });
//
//    // Open register form
//    registerBtn?.addEventListener("click", (e) => {
//        e.preventDefault();
//        home.classList.add("show");
//        registerForm.classList.remove("hidden");
//        loginForm.classList.add("hidden");
//    });
//
//    // Close the form
//    formCloseBtn?.addEventListener("click", () => {
//        home.classList.remove("show");
//    });
//
//    // Toggle password visibility
//    pwShowHide.forEach((icon) => {
//        icon.addEventListener("click", () => {
//            let getPwInput = icon.parentElement.querySelector("input");
//            if (getPwInput.type === "password") {
//                getPwInput.type = "text";
//                icon.classList.replace("uil-eye-slash", "uil-eye");
//            } else {
//                getPwInput.type = "password";
//                icon.classList.replace("uil-eye", "uil-eye-slash");
//            }
//        });
//    });
//
//    // Switch to register form from login form
//    signupBtn?.addEventListener("click", (e) => {
//        e.preventDefault();
//        loginForm.classList.add("hidden");
//        registerForm.classList.remove("hidden");
//    });
//
//    // Switch to login form from register form
//    loginBtn?.addEventListener("click", (e) => {
//        e.preventDefault();
//        registerForm.classList.add("hidden");
//        loginForm.classList.remove("hidden");
//    });
//
//    // Handle login form submission
//    // Securely encrypt and store sensitive data
//function encryptData(data) {
//    return btoa(unescape(encodeURIComponent(data))); // Example encryption (base64 encoding)
//}
//
//// Decrypt sensitive data when needed
//function decryptData(data) {
//    return decodeURIComponent(escape(atob(data))); // Example decryption
//}
//
//document.getElementById("loginForm")?.addEventListener("submit", async (e) => {
//    e.preventDefault();
//    const username = document.getElementById("loginUsername").value;
//    const password = document.getElementById("loginPassword").value;
//
//    if (!username || !password) {
//        const loginError = document.getElementById("loginError");
//        loginError.textContent = "Both username and password must be filled!";
//        loginError.style.display = "block";
//        return;
//    }
//
//    try {
//        const response = await fetch("http://localhost:8080/api/auth/login", {
//            method: "POST",
//            headers: { "Content-Type": "application/json" },
//            body: JSON.stringify({ username, password }),
//        });
//
//        const data = await response.json();
//
//        if (response.ok) {
//            // Encrypt and store the token securely
//            localStorage.setItem("token", encryptData(data.token));
//            localStorage.setItem("username", encryptData(username));
//            localStorage.setItem("userId", encryptData(data.userId));
//
//            showPopup("Login successful!");
//
//            // Redirect based on roles
//            if (username === "legends" && password === "Legends@123") {
//                window.location.href = "admin.html";
//            } else if (data.roles.includes("COACH")) {
//                window.location.href = "coachdashboard.html";
//            } else if (data.roles.includes("ATHLETE")) {
//                window.location.href = "athlete.html";
//            } else {
//                alert("Unauthorized role. Contact support.");
//            }
//        } else {
//            const loginError = document.getElementById("loginError");
//            loginError.textContent = data.message || "Login failed. Please check your credentials.";
//            loginError.style.display = "block";
//        }
//    } catch (error) {
//        console.error("An error occurred during login:", error);
//        alert("An error occurred. Please try again later.");
//    }
//});
//
//    // Handle sign-up form submission
//    document.getElementById("signUpForm")?.addEventListener("submit", async (e) => {
//        e.preventDefault();
//
//        const username = document.getElementById("username").value;
//        const email = document.getElementById("email").value;
//        const password = document.getElementById("password").value;
//        const role = document.getElementById("role").value;
//
//        const passwordRegex = /^(?=.[A-Za-z])(?=.\d)(?=.[!@#$%^&])[A-Za-z\d!@#$%^&*]{8,}$/;
//        if (!passwordRegex.test(password)) {
//            const registerError = document.getElementById("registerError");
//            registerError.textContent =
//                "Password must be at least 8 characters long, contain at least one letter, one number, and one special character.";
//            registerError.style.display = "block";
//            return;
//        }
//
//        if (!username || !email || !password || !role) {
//            const registerError = document.getElementById("registerError");
//            registerError.textContent = "All fields must be filled!";
//            registerError.style.display = "block";
//            return;
//        }
//
//        try {
//            const response = await fetch("http://localhost:8080/api/auth/signup", {
//                method: "POST",
//                headers: {
//                    "Content-Type": "application/json",
//                },
//                body: JSON.stringify({ username, email, password, role }),
//            });
//
//            const data = await response.json();
//
//            if (response.ok) {
//                showPopup(data.message || "Signup successful!");
//                loginForm.classList.remove("hidden");
//                registerForm.classList.add("hidden");
//            } else {
//                const registerError = document.getElementById("registerError");
//                registerError.textContent = data.message || "Sign up failed. Please try again.";
//                registerError.style.display = "block";
//            }
//        } catch (error) {
//            console.error("Error during sign up:", error);
//            const registerError = document.getElementById("registerError");
//            registerError.textContent = "Sign up failed. Please try again.";
//            registerError.style.display = "block";
//        }
//
//    });
//});