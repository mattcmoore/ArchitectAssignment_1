$(document).ready(function(){
    // Show history popup when history button is clicked
    $('#historyButton').click(function() {
        $('#historyPopup').toggle(); // Show or hide the history popup
    });

    // $('#uploadButton').click(function() {
    //     uploadCSV();
    // });
    // Close the popup when the 'X' button is clicked
    $('#closePopup').click(function() {
        $('#historyPopup').hide();
    });

    let isRev = false;
    $('#toggleAsc').click(async function() {
        isRev = await toggleReverse(isRev);
        if($('#toggleAsc').text() == "Aa"){
            $('#toggleAsc').text("aA");
        } else {
            $('#toggleAsc').text("Aa");
        }
        loadHistory();
    });
});

//Uploads .csv onClick
export async function uploadCSV() {
    const fileInput = document.getElementById('csvFile');

    if (fileInput.files.length === 0) {
        console.log("No file selected.");
        return;
    }

    const formData = new FormData();
    formData.append('file', fileInput.files[0]);

    try {
        const response = await fetch('http://127.0.0.1:8080/csv', {
            method: 'POST',
            body: formData,
            mode: 'no-cors',
            credentials: 'same-origin'
        });
        const result = await response.text();
        console.log('Server response:', result);

    } catch (error) {
        console.error('Error uploading file:', error);
    } finally {
        // loadHistory();
    }

}

// Create a function to fetch data with CORS handling
async function fetchUploads() {
    try {
        // Make a GET request with CORS enabled
        const response = await fetch('http://127.0.0.1:8080/uploads', {
            method: 'GET',        // HTTP method
            headers: {            // Optional: Set custom headers if needed
                'Content-Type': 'application/json',
            },
        });

        // Check if the response is successful (status code 200-299)
        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }

        // Parse the JSON response
        const data = await response.json();

        // Log the parsed JSON object to the console
        console.log('Fetched JSON data:', data);
        return data;

    } catch (error) {
        // Handle any errors that occur during the fetch or JSON parsing
        console.error('Error fetching data:', error);
    }
}

async function toggleReverse(isRev) {
    try {
        // Send the POST request
        const response = await fetch('http://127.0.0.1:8080/upload/reverse', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            cors: 'no-cors',
            body: JSON.stringify({ isReverse: isRev }) // Send the current value of isRev
        });

        if (!response.ok) {
            throw new Error(`Request failed with status ${response.status}`);
        }

        const responseData = await response.json();
        console.log('Request succeeded:', responseData);
        return responseData;
    } catch (error) {
        console.error('Request failed:', error);
    }
}

function loadHistory() {
    fetchUploads().then((data) => {
        const itemsContainer = document.getElementById('historyContainer');

        // Clear previous content
        itemsContainer.innerHTML = '';

        // Create container for each response object item
        data.forEach(item => {
            const itemDiv = document.createElement('div');
            itemDiv.className = 'item-container';

            // format LocalDateTime string
            const javaLocalDateTimeString = item['timestamp'];
            const date = dateFns.parse(javaLocalDateTimeString, "yyyy-MM-dd'T'HH:mm:ss", new Date());
            const formattedDate = dateFns.format(date, 'MMM dd, yyyy');

            itemDiv.textContent = item['name'] + '___' + formattedDate + '___' + item['size'] + ' bytes___' + item['status']; // Display the item text
            itemsContainer.appendChild(itemDiv);
        });
    });
}

// Call the fetchItems function on page load
window.onload = loadHistory();