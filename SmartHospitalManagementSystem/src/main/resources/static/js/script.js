console.log("this is script file");

const toggleSidebar = () => {

    if($(".sidebar").is(":visible")) {
        //true
        //band karna hai

        $(".sidebar").css("display","none");
        $(".content").css("margin-left","0%");
    }else{
            //false
            //show karna hai
            $(".sidebar").css("display","block");
            $(".content").css("margin-left","20%");
    }
};

//onst search=()=>{
   // console.log("searching...")
  // let query=$("#search-input").val();

  // if(query==""){
/*$(".search-result").hide();   

}else{


    let url=`http://localhost:8080/search/${query}`;

    fetch(url)
    .then((response)=>{
        return response.json();
    })
.then((data)=>{
    //data...

    let text=`<div class='list-group'>`;
data.forEach((appointment) => {
    text+=`<a href='/patient/show_appointment' class='list-group-item list-group-item-action'>${appointment.name} </a>`
  //  text+=`<a href='/patient/${appointment.cId}/appointment' class='list-group-item list-group-item-action'>${appointment.name} </a>`
    
});


text+=`</div>`;
$(".search-result").html(text);
$(".search-result").show();
});


}
};*/