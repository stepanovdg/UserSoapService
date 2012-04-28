function validateDeleteForm(form) {
	var checkBox = 0;
	var checkedBox = 0;
	var arr = form.elements;
	for ( var i = 0; i < arr.length; i++) {
		if (arr[i].type == "checkbox") {
			checkBox++;
		}

		if (arr[i].type == "checkbox" && arr[i].checked == false) {
			checkedBox++;
		}
	}
	if (checkBox == checkedBox) {
		alert(nonselect);
		return false;
	}
	return confirmDelete();
}

function confirmDelete() {
	return confirm(deleting);
}

function checkDate(date) {
	re = new RegExp(pattern);
	if (date.match(re) != null) {
		if (date.match(re)[0] == date) {
			return true;
		} else
			return false;
	} else
		return false;

}
function isnum(num) {
	if (num == 0)
		return true;
	return res = (num / num) ? true : false;
}

function validateAddForm(form) {
	var counter = 0;
	var errMessage = fill + ": \n";

	if (!form.elements["userMessage.name"].value) {
		counter++;
		errMessage += name + "\n";
	}
	if (!form.elements["userMessage.uid"].value
			|| !isnum(form.elements["userMessage.uid"].value)) {
		counter++;
		errMessage += uid + "\n";
	}
	if (!form.elements["userMessage.jsonData"].value) {
		counter++;
		errMessage += json + "\n";
	}
	if (!form.elements["simpleDate"].value) {
		counter++;
		errMessage += date + "\n";
	}
	if (form.elements["userMessage.jsonData"].value.length > 2048) {
		form.elements["userMessage.jsonData"].value = form.elements["userMessage.jsonData"].value
				.substring(0, 2048);

	}
	if (form.elements["simpleDate"].value != 0) {
		var str = form.elements["simpleDate"].value;
		if (checkDate(str)) {
			if (counter == 0) {
				return true;
			} else {
				alert(errMessage.substring(0, errMessage.length-1));
				return false;
			}
		} else {
			alert(format);
			return false;
		}

	} else {
		alert(errMessage.substring(0, errMessage.length-1));
		alert(errMessage);
		return false;
	}
}
