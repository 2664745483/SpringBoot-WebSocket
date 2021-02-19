var stompClient = null;
var serverPost = "localhost:8080";
function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#notice").html("");
}

function connect() {
    var socket = new SockJS('/endpoint-websocket'); //连接上端点(基站)
    
    stompClient = Stomp.over(socket);			//用stom进行包装，规范协议
    stompClient.connect({}, function (frame) {	
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/game_chat', function (result) {
        	console.info(result)
        	showContent(JSON.parse(result.body));
        });
    });
}


function connect2() {
    var socket = new SockJS('/endpoint-websocket'); //连接上端点(基站)

    stompClient = Stomp.over(socket);			//用stom进行包装，规范协议
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/chat/game_chat', function (result) {
            console.info(result)
            showContent(JSON.parse(result.body));
        });
    });
}

function connectServerINfo() {
    var socket = new SockJS('/endpoint-websocket'); //连接上端点(基站)

    stompClient = Stomp.over(socket);			//用stom进行包装，规范协议
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/server_info', function (result) {
            console.info(result)
            showContent(JSON.parse(result.body));
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
	//当前已经连接到了服务端相当于这个连接在服务器里面
    stompClient.send("/app/v1/chat", {}, JSON.stringify({'content': $("#content").val(),'to': $("#to").val()}) );
}

function showContent(body) {
    $("#notice").append("<tr><td>" + body.content + "</td> <td>"+new Date(body.time).toLocaleString()+"</td></tr>");
}





$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#connect2" ).click(function() { connect2(); });
    $( "#connectServerInfo" ).click(function() { connectServerINfo(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });

});

