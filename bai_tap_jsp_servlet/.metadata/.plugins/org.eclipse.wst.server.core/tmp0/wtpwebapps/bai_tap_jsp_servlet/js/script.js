/**
 * 
 */

// xu ly ngay thang

function formatDate() {
    const dateInput = document.getElementById('date');
    const dateValue = new Date(dateInput.value);
    
    if (isNaN(dateValue)) {
        alert('Invalid date.');
        return;
    }
    
    const day = String(dateValue.getDate()).padStart(2, '0');
    const month = String(dateValue.getMonth() + 1).padStart(2, '0');
    const year = dateValue.getFullYear();
    
    const formattedDate = `${day}/${month}/${year}`;
    document.getElementById('formattedDate').innerText = formattedDate;
}


function deleteMessage(id, name){
		let message = confirm("bạn co muon xoa ", name);
		
		if (message){
			window.location.href = "DeleteEmployee?id=" + id;
		}
		
	}
	
	
// xu ly action trang add	
$("#birthDayEmployee, #dateJoiningBirthday").blur(function(){
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
  var isConfirmed = confirm("Bạn có chắc muốn thêm nhân viên này?");
  
  if (!isConfirmed){
	  event.preventDefault();
  }
})