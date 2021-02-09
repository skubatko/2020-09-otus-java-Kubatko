let stompClient = null;

const setConnected = (connected) => {
  $("#connect").prop("disabled", connected);
  $("#disconnect").prop("disabled", !connected);
  if (connected) {
    $("#user-line").show();
  } else {
    $("#user-line").hide();
  }
  stompClient.send("/app/users", {}, {})
}

const connect = () => {
  stompClient = Stomp.over(new SockJS('/ws-endpoint-users'));
  stompClient.connect({}, (frame) => {
    setConnected(true);
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/response', (user) => showUser(JSON.parse(user.body)));
  });
}

const showUser = (user) => $("#user-line").append("<tr><td>" + user.name + "</td><td>" + user.login + "</td><td>" + user.password + "</td></tr>")

const disconnect = () => {
  if (stompClient !== null) {
    stompClient.disconnect();
  }
  setConnected(false);
  console.log("Disconnected");
}

$(function () {
  $("form").on('submit', (event) => {
    event.preventDefault();
  });
  $("#connect").click(connect);
  $("#disconnect").click(disconnect);
  $("#send").click(sendUser);
});

const sendUser = () => stompClient.send("/app/user", {}, JSON.stringify({
  'name': $("#new-user-name").val(),
  'login': $("#new-user-login").val(),
  'password': $("#new-user-password").val()
}))
