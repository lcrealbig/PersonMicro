import { Injectable } from '@angular/core';
import { Person } from '../../model/person';

@Injectable({
  providedIn: 'root'
})
export class PersonValidationService {
  id: any;
  name: any;
  birthday: any;

  constructor() { }

  isValidDateFormat(dateStr: string): boolean {
    const expectedFormat = /^\d{4}-\d{2}-\d{2}$/;
    if (dateStr.length < 10 && !expectedFormat.test(dateStr)) {
      console.log('in false');
      
        return false;
    }
    try {
        const date = new Date(dateStr);
        const isoString = date.toISOString().split('T')[0];
        console.log('in true ', isoString);

        return isoString === dateStr;
    } catch {
        return false;
    }
}

isIdLengthValid(id: string){
return id.length === 5;
}

isIdPatternValid(id: string): boolean {
  if (id.length === 0) {
    return true;
  }
  return id.length === 5 || /^[A-Za-z]\d{4}$/.test(id);
}

isNameNotEmpty(name: string): boolean{
  return name.length > 0;
}
}
