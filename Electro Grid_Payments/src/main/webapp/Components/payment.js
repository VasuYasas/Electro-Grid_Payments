$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
 {
 $("#alertSuccess").hide();
 }
 $("#alertError").hide();
});

//SAVE============================================
$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();
// Form validation-------------------
var status = validateUserForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }
// If valid------------------------
var type = ($("#hidBill_IDSave").val() == "") ? "POST" : "PUT";
 $.ajax(
 {
 url : "PaymentAPI",
 type : type,
 data : $("#formPayment").serialize(),
 dataType : "text",
 complete : function(response, status)
 {
 onUserSaveComplete(response.responseText, status);
 }
 });
});

function onPaymentSaveComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully saved.");
 $("#alertSuccess").show();
 $("#divUsersGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while saving.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while saving..");
 $("#alertError").show();
 } 
 $("#hidBill_IDSave").val("");
 $("#formPayment")[0].reset();
}

// UPDATE==========================================

$(document).on("click", ".btnUpdate", function(event)
{
$("#hidBill_IDSave").val($(this).data("Bill_ID"));
 $("#UserName").val($(this).closest("tr").find('td:eq(0)').text());
 $("#Card_No").val($(this).closest("tr").find('td:eq(1)').text());
 $("#CVC").val($(this).closest("tr").find('td:eq(2)').text());
 $("#Exp_Date").val($(this).closest("tr").find('td:eq(3)').text());
});


//REMOVE==============================================

$(document).on("click", ".btnRemove", function(event)
{
 $.ajax(
 {
 url : "PaymentAPI",
 type : "DELETE",
 data : "Bill_ID=" + $(this).data("Bill_ID"),
 dataType : "text",
 complete : function(response, status)
 {
 onUserDeleteComplete(response.responseText, status);
 }
 });
});


function onUserDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divUsersGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
}

// CLIENT-MODEL================================================================
function validatePaymentForm()
{
// NAME
if ($("#UserName").val().trim() == "")
 {
 return "Insert UserName.";
 }
// CONTACT
if ($("#Card_No").val().trim() == "")
 {
 return "Insert Card_No.";
 } 
 
// EMAIL-------------------------------
if ($("#CVC").val().trim() == "")
 {
 return "Insert CVC.";
 }

// PASSWORD------------------------
if ($("#Exp_Date").val().trim() == "")
 {
 return "Insert Exp_Date.";
 }
return true;
}




