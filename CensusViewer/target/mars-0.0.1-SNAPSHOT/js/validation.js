
//Check for email and password
function chkForm() {
    
			//to access the form element from login page
	      // alert();
            chkEmail();
            chkPassword();
        }


// function to check Email
        function chkEmail() {
            //testing regular expression
            
            var a = $("#email").val();
            var filter = /^[a-zA-Z0-9]+[a-zA-Z0-9_.-]+[a-zA-Z0-9_-]+@[a-zA-Z0-9]+[a-zA-Z0-9.-]+[a-zA-Z0-9]+.[a-z]{2,4}$/;
            //if it's valid email
            if (filter.test(a)) {
                $('#tube').animate({ width: 'show' });
                return false;
            }
            else {

                $('#tube').animate({ width: 'hide' });
                return false;
            }
        }

        function chkPassword() {
            
            var pass1 = $("#password");

            if (pass1.val().length < 5) {
                $('#tube2').animate({ width: 'hide' });
                return false;
            }
            else {
                $('#tube2').animate({ width: 'show' });
            }

        }
   /*     function alert()
        {
        	$(document).ready(function(){
      		  
       		 $("#login").click(function() {
       		     
       		    $("#action").val($("#login").val()); 

       		    alert("Text: " + $("#action").val());
       		});}*/
        