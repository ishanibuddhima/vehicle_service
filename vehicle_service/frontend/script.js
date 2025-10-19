const apiBase = "http://localhost:8081/api/vehicles";
const form = document.getElementById("vehicleForm");
const tableBody = document.querySelector("#vehicleTable tbody");

// Fetch all vehicles
async function loadVehicles() {
  const response = await fetch(apiBase);
  const vehicles = await response.json();
  tableBody.innerHTML = "";
  vehicles.forEach(v => {
    const row = `
      <tr>
        <td>${v.id}</td>
        <td>${v.vehicleType}</td>
        <td>${v.serviceYear}</td>
        <td>${v.ownerName}</td>
        <td>${v.cost}</td>
        <td>
          <button onclick="editVehicle(${v.id})">✏️ Edit</button>
          <button onclick="deleteVehicle(${v.id})">🗑️ Delete</button>
        </td>
      </tr>
    `;
    tableBody.innerHTML += row;
  });
}

// Add or Update vehicle
form.addEventListener("submit", async (e) => {
  e.preventDefault();
  const id = document.getElementById("vehicleId").value;
  const vehicle = {
    vehicleType: document.getElementById("vehicleType").value,
    serviceYear: document.getElementById("serviceYear").value,
    ownerName: document.getElementById("ownerName").value,
    cost: document.getElementById("cost").value
  };

  const method = id ? "PUT" : "POST";
  const url = id ? `${apiBase}/${id}` : apiBase;

  await fetch(url, {
    method,
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(vehicle)
  });

  form.reset();
  document.getElementById("vehicleId").value = "";
  loadVehicles();
});

// Edit vehicle
async function editVehicle(id) {
  const response = await fetch(`${apiBase}/${id}`);
  if (!response.ok) {
    alert("Vehicle not found");
    return;
  }
  const vehicle = await response.json();
  document.getElementById("vehicleId").value = vehicle.id;
  document.getElementById("vehicleType").value = vehicle.vehicleType;
  document.getElementById("serviceYear").value = vehicle.serviceYear;
  document.getElementById("ownerName").value = vehicle.ownerName;
  document.getElementById("cost").value = vehicle.cost;
}

// Delete vehicle
async function deleteVehicle(id) {
  if (confirm("Are you sure you want to delete this vehicle?")) {
    await fetch(`${apiBase}/${id}`, { method: "DELETE" });
    loadVehicles();
  }
}

// Load data when page opens
loadVehicles();
