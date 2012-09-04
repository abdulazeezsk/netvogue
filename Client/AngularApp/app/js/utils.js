function addresstostring(contactinfo) {
	var str =  contactinfo['address'] + '\n';
    str +=  contactinfo['city'] + '\n';
    str +=  contactinfo['zip'];
    return str;
}