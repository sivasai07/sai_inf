const usersname = localStorage.getItem("username") || sessionStorage.getItem("username");
const userDetailsElement = document.getElementById("userDetails");
userDetailsElement.textContent = `COACH - ${usersname}`;

document.addEventListener("DOMContentLoaded", function () {
    // Start checking session every second
    sessionCheckInterval = setInterval(checkSession, 1000);

    // Retrieve stored data from localStorage or sessionStorage
    const token = localStorage.getItem("token") || sessionStorage.getItem("token");
    const username = localStorage.getItem("username") || sessionStorage.getItem("username");
    const userId = localStorage.getItem("userId") || sessionStorage.getItem("userId");
    const roles = localStorage.getItem("roles") || sessionStorage.getItem("roles");

    // Initial session check
    if (!username) {
        logout();
        return;
    }

    // Store retrieved data in variables
    let userToken = token;
    let userUsername = username;
    let userUserId = userId;
    let userRoles = roles;

    // Display user details on the page if the user is logged in
    const userDetailsElement = document.getElementById("userDetails");
    if (userDetailsElement) {
        if (userToken && userUsername && userUserId) {
            userDetailsElement.textContent = `COACH - ${userUsername}`;
        } else {
            userDetailsElement.textContent = "No user details found. Please log in again.";
            return;
        }
    } else {
        console.warn("User details element not found");
    }

    // Log the retrieved details for debugging purposes
    console.log("User Token:", userToken);
    console.log("Username:", userUsername);
    console.log("User ID:", userUserId);
    console.log("Roles:", userRoles);

    // If user is logged in, fetch the coach ID
    if (userUserId) {
        fetchCoachId(userUserId);
    } else {
        return;
    }

    // Fetch Coach ID from the API
    async function fetchCoachId(userId) {
    if (!userId) {
        displayError("User ID not found.");
        return;
    }

    try {
        const response = await fetch(`http://localhost:8080/api/coaches/byUserId/${userId}`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${userToken}`,
            },
        });

        if (response.ok) {
            const coachData = await response.json(); // Parse the JSON response

            if (coachData && coachData.coachId) {
                console.log("Fetched Coach Data:", coachData);

                // Extract coachId and username
                coachId = coachData.coachId;
                console.log("id",coachId);
                fetchEvents();
                initDashboardStats();


                const coachName = `${coachData.firstName} ${coachData.lastName}`;

                // Store data in localStorage
                localStorage.setItem("coachId", coachId);
                localStorage.setItem("coachName", coachName);

                // Display coach name
                const userDetailsElement = document.getElementById("userDetails");
                if (userDetailsElement) {
                    userDetailsElement.textContent = `COACH - ${coachName}`;
                }

                // Fetch athletes assigned to this coach
            } 
        } else {
            console.error("Failed to fetch coach data. Status:", response.status);

        }
    } catch (error) {
        console.error("Error fetching Coach ID:", error);

    }
}
});