function prepareEventListeners(){
    var addButton = document.getElementsByClassName("mengenfeld");
    var re = /^\d+$/;
    addButton.onsubmit = function(){
       if (document.getElementsByClassName("amount").value == "" || !re.test(document.getElementsByClassName("amount").value)){  
         document.getElementsByClassName("error1").innerHTML="Falsche Eingabe!"
         
        return false;
       }
       
       
    }
}

window.onload = function(){
    prepareEventListeners();
}