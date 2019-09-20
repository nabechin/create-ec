$(function(){
    	calc_total();
    	$("#item").on("change",function(){
    		calc_total();
    	});
    	$(".select").on("change",function(){
    		console.log("AAA");
    		calc_total();
    	});
    	$("#total").on("change",function(){
    		calc_total();
    	});
    	function calc_total(){
    		var item_size=$("#item:checked").val();
            var topping_number=$(".select:checked").length;
            var item_number=$("#total").val();
            if(item_size=="M"){
            	var topping_price=topping_number*200;
            	var item_price=$(".M").data('price');
            }else{
            	var topping_price=topping_number*300;
            	var item_price=$(".L").data('price');
            }
            var price=(topping_price+item_price)*item_number;
            $("#total-price").text(price.toLocaleString());
    	}
    });
