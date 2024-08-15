<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Insert title here</title>
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
         rel="stylesheet" 
         integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
         crossorigin="anonymous">
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
         integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
         crossorigin="anonymous"></script>
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
      <script src="https://unpkg.com/dayjs@1.8.21/dayjs.min.js"></script>
      <link href="<c:url value="/css/base.css"/>" rel="stylesheet" type="text/css" />
   </head>
   <body>
   		
   	
   	  <jsp:include page="header.jsp"></jsp:include>
   	  
      <div class="container border" style="margin-top:100px; width:800px; border-radius: 10px;">
      	<center style="padding-top: 20px;"><h3>Add Employyee</h3></center>
         <form id="updateForm" action="employee" method="post">
            <table class="table">
              
               <tr>
                  <td><label for="nameEmployee" class="form-label">EmployeeName</label></td>
                  <td>
                     <input type="text" class="form-control" id="nameEmployee" aria-describedby="emailHelp" name="nameEmployee" required="required" pattern="^[^\d]+$">
                     <span class="rq">${msgUserName}</span>
                  </td>

               </tr>
               <tr>
                  <td><label for="birthDayEmployee" class="form-label">EmployeeBirthDate</label></td>
                  <td>
                     <input type="date" class="form-control" id="birthDayEmployee" name="birthDayEmployee" required="required">
                     <span class="rq">${msgEmployeeBirthDate}</span>
                  </td>
                  
               </tr>
               <tr>
                  <td><label for="emailEmployee" class="form-label">Email address</label></td>
                  <td>
                     <input type="email" class="form-control" id="emailEmployee" aria-describedby="emailHelp" name="emailEmployee" required="required">
                     <span class="rq">${msgEmail}</span>
                     
                  </td>
               </tr>
               <tr>
                  <td><label for="ageEmployee" class="form-label">EmployeeAge</label></td>
                  <td>
                     <input type="number" class="form-control" id="ageEmployee" aria-describedby="emailHelp" value="" readonly name="ageEmployee">
                     <span class="rq">${msgEmployeeAge}</span>
                  </td>
               </tr>
               <tr>
                  <td><label for="inlineRadio1" class="form-label">EmployeeGender</label></td>
                  <td>
                  	<div class="container">
                  		<div class="form-check form-check-inline">
	                        <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio1" value="male">
	                        <label class="form-check-label" for="inlineRadio1">Male</label>
	                     </div>
	                     <div class="form-check form-check-inline">
	                        <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio2" value="female">
	                        <label class="form-check-label" for="inlineRadio2">Female</label>
	                     </div>
	                     <div class="form-check form-check-inline">
	                        <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio3" value="orther">
	                        <label class="form-check-label" for="inlineRadio3">Orther</label>
	                     </div>
                  	</div>
                     
                     <div>
                     	<span class="rq">${msgGender}</span>
                     </div>
                     
                  </td>
               </tr>
               <tr>
                  <td><label for="addressEmployee" class="form-label">EmployeeAddress</label></td>
                  <td>
                     <textarea  class="form-control" id="addressEmployee" aria-describedby="emailHelp" name="addressEmployee"></textarea>
                 
                  </td>
               </tr>
               <tr>
                  <td><label for="exampleInputEmail1" class="form-label">Position</label></td>
                  <td>
                     <select class="form-control" id="selection" name="position">
                        <option value="employee">Employee</option>
                        <option value="boss">Boss</option>
                     </select>
                     <span class="rq">${msgPosition}</span>
                  </td>
               </tr>
               <tr>
                  <td><label for="dateJoiningBirthday" class="form-label">DateOfJoiningTheCompany</label></td>
                  <td>
                		<input type="date" class="form-control" id="dateJoiningCompany" name="dateJoiningCompany" required="required" max="">
                     	<span class="rq">${msgDateJoinCompany}</span>
                  </td>
               </tr>
            </table>
             <div class="grid" style="padding:20px;">
            	<button type="submit" class="btn btn-primary">Add New</button>
            </div>
         </form>
      </div>
   </body>
 <script>
 
 $("#birthDayEmployee, #dateJoiningCompany").blur(function(){
	    const dateValue = new Date($(this).val());
	    
	    if (isNaN(dateValue)) {
	        return '';
	    }
	    
	    const day = String(dateValue.getDate()).padStart(2, '0');
	    const month = String(dateValue.getMonth() + 1).padStart(2, '0');
	    const year = dateValue.getFullYear();
	  
	    var text = day + "/" + month + "/" + year;
	    $(this).attr("type","text");
	    $(this).val(text);
});
 
 
 $("#birthDayEmployee").blur(function(){
		
		var date1 = new Date();
		
		var values = $(this).val();
		var yeard = parseInt(values.substr(6, values.length));

		currentYear = date1.getFullYear();
		
		age = currentYear - yeard;
		$("#ageEmployee").val(age);
	});

	$("#updateForm").submit(function(){
		
		$("#inputEmployeeId").disabled = false;	
		
		
	})
	    
	    

	      
	$("form").on('submit', function(){
		
	  var inputDate = new Date(document.getElementById('dateJoiningCompany').value);
	  var today = new Date();
	  
	  
	  var isConfirmed = confirm("Bạn có chắc muốn thêm nhân viên này?");
	  
	  if (!isConfirmed){
		  event.preventDefault();
	  }
	})
	
	
	// Lấy ngày hiện tại
    var today = new Date().toISOString().split('T')[0];
    // Đặt giá trị max của input là ngày hiện tại
    document.getElementById('dateJoiningCompany').setAttribute('max', today);
    // Thiết lập giá trị max là ngày hiện tại
    document.getElementById('dateJoiningCompany').max = new Date().toISOString().split("T")[0];

    /*function formatDate() {
        const dateInput = document.getElementById('dateJoiningCompany').value;
        if (dateInput) {
            const [year, month, day] = dateInput.split("-");
            const formattedDate = `${day}/${month}/${year}`;
            document.getElementById('dateJoiningCompany').value = formattedDate;
            return;
        } 
    }*/
 </script>
   
</html>