let Tbody = document.getElementById("Tbody");
 
//let uuid=localStorage.getItem("uuid") || "";
let uuid="wt0Ob5";
let baseURL = `http://localhost:8080`;
getProduct()
function getProduct(){
    fetch(`${baseURL}/allOperators`)
    .then((Response)=>{
        return Response.json()
    })
    .then((data)=>{
       // console.log(data);
        //console.log(data);
        showData(data)
    })
}



function showData(data){
    Tbody.innerHTML = null;
    let htmlData = data.map((el)=>getCard(el.operatorId,el.name,el.email,el.username,el.password,el.mobile,el.city))
    Tbody.innerHTML = htmlData.join(" ");
    
    let tr = document.getElementsByClassName("edit");
    let deletebtn = document.getElementsByClassName("delete");
   //  console.log(tr);
    for(let k of tr){
        console.log(k);
        k.addEventListener("click",(e)=>{
            let rowId = e.target.dataset.id;
           
            toggleBilling(rowId,e.target)
        })
    }

    for(let btn of deletebtn){
        btn.addEventListener("click",(e)=>{
            let id = e.target.dataset.id;
            deleteOperater(id)
            // alert(id)
        })
    }

}
function toggleBilling(rowId,element) {
    // Select the billing text fields
    console.log(rowId)
    const billingItems = document.querySelectorAll(`#ID${rowId} input[type="text"]`);
    console.log(billingItems);
    let obj= {}
    // Toggle the billing text fields
    billingItems.forEach((item) => {
        item.disabled = !item.disabled;
        let el = item.getAttribute("id")
      //  console.log(el);
        if(!item.disabled){
            element.innerText = "Save"
            item.style.border ="1px solid black"  
            item.style.width ="100%"   
        }else{
            obj[el] = item.value
            // console.log(1)
            // console.log(obj);
            element.innerText = "Edit"
            item.style.border ="none"  
            item.style.width ="100%"
            
        }
    });
    // console.log(obj)
    // console.log(billingItems[0].disabled == undefined)
    if(Object.keys(obj).length !== 0){
       // console.log(obj,rowId)
        updateData(obj,rowId)
    }
  }

 function updateData(obj,id){
    const intId = parseInt(id);
 console.log(id);
      let operatorId="operatorId";
    if(obj[operatorId] == undefined)obj[operatorId] = intId;
    console.log(obj);
    fetch(`${baseURL}/modifyOperator/${intId}`,{
        method : "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body : JSON.stringify(obj)
      })
      setTimeout(()=>{
        getProduct()
    },1200)
  }

  function deleteOperater(id){
    const intId = parseInt(id);
    fetch(`${baseURL}/deleteOperator/${intId}`,{
        method : "DELETE"
    })
    setTimeout(()=>{
        getProduct()
    },1200)
  }

function getCard(id,Name,email,username,password,mobile,city){
    let imgURL="https://assets.volvo.com/is/image/VolvoInformationTechnologyAB/1860x1050-volvo-9700-CGI1?qlt=82&wid=1024&ts=1656931444230&dpr=off&fit=constrain";
    if(id%2==0){
       imgURL="https://img.etimg.com/thumb/width-1200,height-900,imgsize-216810,resizemode-75,msid-94221038/news/bengaluru-news/bengalureans-can-soon-enjoy-double-decker-bus-rides-in-new-e-avatar.jpg";
   }
  
    return `
    <tr id=ID${id}>
        <td>${id}</td>
        
        <td><input type="text" id="name" value="${Name}"  disabled></td>
    
        <td><input type="text" id="email" value=${email}  disabled></td>
        <td><input type="text" id="username" value=${username}  disabled></td>
        <td><input type="text" id="password" value=${password}  disabled></td>
        <td><input type="text" id="mobile" value=${mobile}  disabled></td>
        <td><input type="text" id="city" value=${city}  disabled></td>
        <td class="edit" data-id=${id} >Edit</td>
        <td class="delete" data-id=${id}>Delete</td>
    </tr>
    `
}

let logout=document.getElementById("out")
let log=document.getElementById("logout");
log.addEventListener("click",()=>{
    logout.style.display = "inline"
    setTimeout(() => {
        logout.style.display = "none";
        window.open("./index.html")
    }, 2000)
})