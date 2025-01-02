
function checkSession() {
    const username = localStorage.getItem("username");
    if (!username) {
        console.log("Session invalid: Username not found");
        logout();
    }
}
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

            userDetailsElement.textContent = `ATHLETE - ${userUsername}`;
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

    // If user is logged in, fetch the athlete ID
    if (userUserId) {
        fetchAthleteId(userUserId);
    } else {
        return;
    }

    // Fetch Athlete ID from the API
    async function fetchAthleteId(userId) {
        if (!userId) {
            displayError("User ID not found.");
            return;
        }

        try {
            const response = await fetch(`http://localhost:8080/api/athletes/getIdByUserId/${userId}`, {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${userToken}`,
                },
            });

            if (response.ok) {
                const fetchedAthleteId = await response.text();

                if (fetchedAthleteId) {
                    console.log("Fetched Athlete ID:", fetchedAthleteId);
                    athletesid = fetchedAthleteId;
                    localStorage.setItem("athleteId", fetchedAthleteId);
                    fetchRegisteredEvents(fetchedAthleteId);
                } else {
                }
            } else {
            }
        } catch (error) {
            console.error("Error fetching Athlete ID:", error);
        }
    }
}
);