//let uuid=localStorage.getItem("uuid") || "";
let uuid="wt0Ob5";
let baseURL = `http://localhost:8080`;
let Operater = 0;
let Customer = 0;
let departement=0;
let resid=document.getElementById("res-id");
let pending=document.getElementById("pending")
let completed=document.getElementById("Completed");
fetchData("allOperators")
fetchData("allCustomer")
fetchData("AllDepartement")
setTimeout(() => {
   // console.log(Operater)
    showGraph()
}, 2000)

function fetchData(Query) {
   
    fetch(`${baseURL}/${Query}`)
        .then((Response) => {
            return Response.json()
        })
        .then((data) => {
            console.log(data);
            
            Query == "allOperators" ? Operater = data.length : Query=="AllDepartement" ?departement=data.length: Customer=data.length;
            
        })

}






function showGraph() {
    google.charts.load('current', { 'packages': ['corechart'] });
    google.charts.setOnLoadCallback(drawChart);

    function drawChart() {
        var data = google.visualization.arrayToDataTable([
            ['Contry', 'Mhl'],
            ['Operater',Operater],
            ['Customers',Customer],
            ['Departement',departement],
    
        ]);

        var options = {
            title: 'Company Growth Chart',
           // is3D: true
        };

        var chart = new google.visualization.BarChart(document.getElementById('myChart'));
        chart.draw(data, options);
    }
}
// let logout=document.getElementById("out")
// let log=document.getElementById("logout");
// log.addEventListener("click",()=>{
//     logout.style.display = "inline"
//     setTimeout(() => {
//         logout.style.display = "none";
//         window.open("./index.html")
//     }, 2000)
// })
document.addEventListener("DOMContentLoaded", function () {
    // Add event listener to the "Logout" link
    var logoutLink = document.getElementById("logout");
    logoutLink.addEventListener("click", function (event) {
      event.preventDefault(); // Prevent the default link behavior
      Swal.fire({
        title: "Logout Confirmation",
        text: "Are you sure you want to logout?",
        icon: "warning",
        showCancelButton: true,
        confirmButtonText: "Logout",
        cancelButtonText: "Cancel",
        confirmButtonColor: "#d33",
        cancelButtonColor: "#3085d6",
      }).then((result) => {
        if (result.isConfirmed) {
          // Handle the logout action here (replace with your actual logout logic)
          Swal.fire({
            title: "Logged Out!",
            text: "You have been successfully logged out.",
            icon: "success",
            confirmButtonColor: "#3085d6",
          }).then(() => {
            // Redirect to the login page or any other designated page for logging out
            window.location.href = "index.html"; // Replace "login.html" with the actual logout page URL
          });
        }
      });
    });
  });
function showIssue(){
   fetch(`${baseURL}/operator/AllIssue`)
   .then((Response) => {
   return Response.json()
})
.then((data) => {
    console.log(data);
    let p=0;
     let r=0;
    data.forEach((el)=>{
      if(el.status=='PENDING')
          p++;
          else
          r++;
    })
    console.log(p,r);
  pending.innerText=" PENDING : "+p
  completed.innerText=" RESOLVED : "+r;
}
)}
showIssue();