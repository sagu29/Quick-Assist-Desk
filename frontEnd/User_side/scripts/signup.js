let S_btn=document.getElementById("signup_submit");
let username=document.getElementById("UserName");
let Email=document.getElementById("email");
let Name=document.getElementById("F_Name");
let city=document.getElementsByName("City");
  let phone=document.getElementById("Phone");
  let pass1=document.getElementById("password");
  let baseURL = `http://localhost:8080`;
let User_Data=JSON.parse(localStorage.getItem("login"))||[];
let login_alert=document.getElementById("Login_password_alert")


 S_btn.addEventListener("click",(e)=>{ 
  e.preventDefault();
  let g_value="";
  
    
  //console.log(g_value);
  if(Email.value!="" && pass1.value!=""){
         console.log("hii");
              fetch(`${baseURL}/addCustomer`,{
                  method:'POST',
                  headers: {
                      'Content-Type': 'application/json'
                      
                    },
                    body: JSON.stringify({
                        "email":Email.value,
                        "password":pass1.value,
                        "name":Name.value,
                        "username":username.value ,
                        "mobile":phone.value,
                        "city":city.value
                      })
              }) .then((response) => response.json())
              .then((data) => {
                console.log('Success:', data);
               // getData();
                document.getElementById("success").style.display="block";
              })
              .catch((error) => {
                console.log('Error:', error);
              });

                  
      }
    }
    )
  
      

 
//  async function getData() {
//    let res=await fetch(`${baseurl}/users`) 
//    let data=await res.json();
//    // console.log(data);
//     localStorage.setItem("login",JSON.stringify(data))
//  }

// getData();
 function login(){
   let email=document.getElementById("L-email").value;
   let pass=document.getElementById("L-password").value;
   let is=false;
   console.log(email,pass);

     if(email=="admin" && pass=="admin"){
        is=true;
        console.log(is);
      //localStorage.setItem("user_login",JSON.stringify(el));
      setTimeout(() => {
        window.location.href="./Admin_Home.html"
      }, 2000);
    
     }
     
     

   if(is){
  document.getElementById("para").innerText=`Welcome ${email}`
  document.getElementById("login_success").style.display="block"
  document.getElementById("Login_password_alert").style.display="none"
   }
   else
    document.getElementById("Login_password_alert").style.display="flex"
 }
//  email.addEventListener("focusout",(e)=>{
//       User_Data.filter((el)=>{
//         if(el.email===email.value){
//           document.getElementById(`${e.target.id}_warning`).innerText = `Already Login`;
//         }
//       })
//  })