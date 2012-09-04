function addresstostring(contactinfo) {
	var str = "";
	if(contactinfo.hasOwnProperty('address')) {
		str =  contactinfo['address'] + '\n';
	}
	if(contactinfo.hasOwnProperty('city')) {
    	str +=  contactinfo['city'] + '\n';
	}
	if(contactinfo.hasOwnProperty('zip')) {
    	str +=  contactinfo['zip'];
	}
	return str;
}