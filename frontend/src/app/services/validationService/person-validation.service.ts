import { Injectable } from '@angular/core';
import { PersonService } from '../personService/person.service';

@Injectable({
  providedIn: 'root'
})
export class PersonValidationService {

  constructor(private personService: PersonService) { }

  isValidDateFormat(dateStr: string): boolean {
    const expectedFormat = /^\d{4}-\d{2}-\d{2}$/;
    if (dateStr.length < 10 && !expectedFormat.test(dateStr)) {      
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

  isIdPatternValid(id: string): boolean {
    if (id.length === 0) {
      return true;
    }
    return id.length === 5 && /^[A-Za-z]\d{4}$/.test(id);
  }

  isFieldNotEmpty(input: string): boolean{
    return input.length > 0;
  }

  isIdUnique(personId: string) {
    return this.personService.isPersonIdUnique(personId);
  }
}
