 <%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
		rel="stylesheet" 
		integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
		crossorigin="anonymous">
<link href="<c:url value="/css/base.css"/>" rel="stylesheet" type="text/css" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
		crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>


</head>



<body style="background-color: #e3f2fd;">



	<!-- -------------------------------------------------------------------------------DIV NAV -------------------------------- -->
	<!-- 
	
		<nav class="navbar navbar-expand-lg bg-light">
      <div class="container-fluid">
         <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
               <a class="navbar-brand" href="home?page=1">
                  <h4>Employee Management Application</h4>
               </a>
            </ul>
            <ul class="d-flex" role="search">
               <h5 class="p-2 bg-light text-center">Hello, ${nameAdmin}</h5>
               
               <a href="logout" class="btn btn-link" style="margin-left:30px;">Logout</a>
            </ul>
         </div>
      </div>
   </nav>
	
	 -->
   <jsp:include page="header.jsp"></jsp:include>
   <div class="mx-auto" style="width: 400px; margin-top: 50px;">
      <h3>List of Employee</h3>
   </div>
   
   <!------------------------------------- Hien thi table ---------------------------------------------------->
   
   <!-- cũ -->
   <div class="container d-flex">
      <div class="p-2 flex-grow-1">
         <form action="home" class="d-flex" role="search">
            <input class="form-control" style="width:300px;" type="search" placeholder="Search" aria-label="Search" name="search" value="${search}">
            <button class="btn btn-outline-success" type="submit">Search</button>
         </form>
      </div>
      <div class="d-flex justify-content-center align-items-center">
         
         <a class="btn btn-primary p-3" href="employee" role="button">Add New Employee</a>
      </div>
   </div>
   <div  style="margin: 50px 100px 0 100px;" >
   		<div class="table-responsive">
   			<table class="table align-middle table-edge table-hover table-nowrap mb-0 table-striped">
         <thead class="thead-light">
            <tr>
               <th scope="col" class="w-60px">EmployeeID</th>
               <th scope="col">EmployeeName</th>
               <th scope="col">EmployeeEmail</th>
               <th scope="col">EmployeeBirthDate</th>
               <th scope="col">EmployeeAge</th>
               <th scope="col">EmployeeGender</th>
               <th scope="col">EmployeeAddress</th>
               <th scope="col">Position</th>
               <th scope="col">DateOfJoinigTheCompany</th>
               <th scope="col" >Action</th>
            </tr>
         </thead>
         <tbody>
         	<c:if test="${fn:length(employeeList) > 0}">
         		<c:forEach items="${employeeList}" var="o">
		            <tr>
		               <th scope="row"><button  class="employeeId btn btn-link" style="border:none;" onclick="findById('${o.employeeId}')">${o.employeeId}</button></th>
		               <td id="name">${o.employeeName}</td>
		               <td>${o.employeeEmail}</td>
		               <td style="text-align:center;">${o.employeeBirthDate}</td>
		               <td style="text-align:center;">${o.employeeAge}</td>
		               <td>${o.employeeGender}</td>
		               <td>${o.employeeAddress}</td>
		               <td>${o.position.getPosition()}</td>
		               <td style="text-align:center;">${o.dateOfJoiningTheCompany}</td>
		               <td style="text-align:center;">
		               		<div class="d-flex flex-row h-full">
		               			
		                  		<a href="EditEmployee?id=${o.employeeId}" style="margin-left:10px,">edit</a>
		                  		<button type="button"  class="btn btn-link" style="margin-top: -8px;" onclick="deleteMessage(`${o.employeeId}`, `${o.employeeName}`)">delete</button>
		               		</div>	
		                  
		               </td>
		            </tr>
	            </c:forEach>
         	</c:if>
         	<c:if test="${fn:length(employeeList) == 0}">
         		<tr>
		            <td colspan="10" style="text-align: center; color: red;'">No records</td>
		        </tr>
         	</c:if>
         	
         </tbody>
      </table>
   		</div>
    
   

	      
   
   
   
    <!------------------------------------- Tao pop-up ---------------------------------------------------->
   
	<div id="myPopup" class="popup">
	    <div class="popup-content">
	        <span class="close-btn" id="closePopup">&times;</span>
	        
	        
	        <table class="table">
              	<tr>
              		<h3>Employee Information</h3>
              	</tr>
               <tr>
                  <td><label for="nameEmployee" class="form-label">EmployeeName</label></td>
                  <td>
                     <input type="text" class="form-control" id="nameEmployee" aria-describedby="emailHelp" name="nameEmployee" required="required" pattern="[a-zA-Z\s]+" disabled>
                  </td>
               </tr>
               <tr>
                  <td><label for="birthDayEmployee" class="form-label">EmployeeBirthDate</label></td>
                  <td>
                     <input type="date" class="form-control" id="birthDayEmployee" name="birthDayEmployee" required="required" disabled>
                  </td>
               </tr>
               <tr>
                  <td><label for="emailEmployee" class="form-label">Email address</label></td>
                  <td>
                     <input type="email" class="form-control" id="emailEmployee" aria-describedby="emailHelp" name="emailEmployee" required="required" disabled>
                     <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
                  </td>
               </tr>
               <tr>
                  <td><label for="ageEmployee" class="form-label">EmployeeAge</label></td>
                  <td>
                     <input type="number" class="form-control" id="ageEmployee" aria-describedby="emailHelp" value="0" name="ageEmployee" disabled>
                  </td>
               </tr>
               <tr>
                  <td><label for="inlineRadio1" class="form-label">EmployeeGender</label></td>
                  <td>
                     <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio1" value="male" disabled >
                        <label class="form-check-label" for="inlineRadio1">Male</label>
                     </div>
                     <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio2" value="female" disabled>
                        <label class="form-check-label" for="inlineRadio2">Female</label>
                     </div>
                     <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio3" value="orther" disabled>
                        <label class="form-check-label" for="inlineRadio3">Orther</label>
                     </div>
                  </td>
               </tr>
               <tr>
                  <td><label for="addressEmployee" class="form-label">EmployeeAddress</label></td>
                  <td>
                     <textarea  class="form-control" id="addressEmployee" aria-describedby="emailHelp" name="addressEmployee" disabled></textarea>
                  </td>
               </tr>
               <tr>
                  <td><label for="exampleInputEmail1" class="form-label">Position</label></td>
                  <td>
                     <select class="form-control" id="selection" name="position" disabled>
                        <option value="employee">Employee</option>
                        <option value="boss">Boss</option>
                     </select>
                  </td>
               </tr>
               <tr>
                  <td><label for="dateJoiningBirthday" class="form-label">DateOfJoiningTheCompany</label></td>
                  <td>
                     <input type="date" class="form-control" id="dateJoiningCompany" name="dateJoiningBirthday" required="required" max="" disabled>
                  </td>
               </tr>
            </table>
	    </div>
	</div>
	
	
   <!-- tao phan trang -->
   <div class="d-flex justify-content-between mt-3">
      <div class="p-2 flex-grow-1">
      	<c:if test="${countEmployee == 0}">
      		<p>Showing 0 - 0 of 0 result</p>
      	</c:if>
         <c:if test="${countEmployee >= 1}">
      		<p>Showing 1 - ${fn:length(employeeList)} of ${countEmployee} result </p>
      	</c:if>
      </div>
      <div class="d-flex justify-content-end">
         <nav aria-label="Page navigation example">
            <ul class="pagination">
            	<c:if test="${countEmployee >= 1}">
            		<li class="page-item"><a class="page-link" href="">Previous</a></li>
	               <c:forEach begin="1" end="${countEmployee /10 + 1}" var="i">
		               <li class="page-item <c:if test='${i == currentPage}'>active</c:if>"><a class="page-link" href="home?page=${i}&search=${search}">${i}</a></li>
		             
	               </c:forEach>
	               <li class="page-item"><a class="page-link" href="#">Next</a></li>
            	</c:if>
               
            </ul>
         </nav>
      </div>
   </div>
   
</body>



<script>


	
	//Khi nhấn nút "Show Popup"
	function findById(param){
		$.ajax({
			url: "viewDetail",
			type: 'GET',
			contentType: 'application/json',
			data: {
				employyId: param
			},
			success: function(result) {	
				
				result = JSON.parse(result);
				console.log(result);
				
				document.getElementById("nameEmployee").setAttribute('value', result.employeeName);
				document.getElementById("birthDayEmployee").setAttribute('value', result.employeBirthDay);
				document.getElementById("emailEmployee").setAttribute('value', result.emailEmployee);
				document.getElementById("ageEmployee").setAttribute('value', result.employeeAge);
				document.getElementById("addressEmployee").value = result.employeeAddress;
				document.getElementById("dateJoiningCompany").setAttribute('value', result.employeeDateOfJoiningTheCompany);	
				
				
				
				document.getElementById("selection").value = result.position;
				
				
				
				var gender = result.employeeGender;
				var radios = document.getElementsByName("inlineRadioOptions");
				for (let i = 0; i <= radios.length; i++){
					if (radios[i].value == gender){
						radios[i].checked = true;
					}
				}
			
			},
			error: function() {
				alert("Lỗi không lấy được dữ liệu")
			}
		});
		$("#myPopup").show();
		
	}
	

	
	// Khi nhấn nút đóng
	$('#closePopup').on('click', function() {
	    $('#myPopup').hide(); // Ẩn popup
	});
	
	// Khi nhấn ra ngoài vùng nội dung của popup
	$(window).on('click', function(event) {
	    if ($(event.target).is('#myPopup')) {
	        $('#myPopup').hide(); // Ẩn popup
	    }
	});
	
	function deleteMessage(id, name){
		let text = "bạn co muon xoa " + name;
		let message = confirm(text);
		
		if (message){
			window.location.href = "DeleteEmployee?id=" + id;
		}
		
	}
	

	
	
	
	let tdElements = document.querySelectorAll("td");
	
	tdElements.forEach(function(td){
		const months = ["01","02","03","04","05","06","07","08","09","10","11","12"];
		
	    if (/^\d{4}-\d{2}-\d{2}$/.test(td.textContent)) {
	            var d = new Date(td.textContent);
	           	
	            var date = (Number(d.getDate()) < 10) ? '0'+d.getDate() : d.getDate();
	            let month = months[d.getMonth()];
	            //var month = String(Number(month) + 1;
	            td.textContent = month  + "/" + date + "/" + d.getFullYear();
	        }
	})

	
	
</script>
</html>