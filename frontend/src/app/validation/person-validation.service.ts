import { Injectable } from '@angular/core';
import { Person } from '../model/person';

@Injectable({
  providedIn: 'root'
})
export class PersonValidationService {
  id: any;
  name: any;
  birthday: any;

  constructor() { }

  isIdPatternCorrect(id: string): boolean{
    const idPattern = /^[A-Za-z]\d{4}$/;
    return idPattern.test(id);
  }

  isValidDateFormat(dateStr: string): boolean {
    const expectedFormat = /^\d{4}-\d{2}-\d{2}$/;
    if (!expectedFormat.test(dateStr)) {
        return false;
    }
    try {
        const date = new Date(dateStr);
        const isoString = date.toISOString().split('T')[0];
        return isoString === dateStr;
    } catch {
        return false;
    }
}

}
