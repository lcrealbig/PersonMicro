import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Person } from 'src/app/model/person';
import { PersonService } from 'src/app/service/person.service';
import { PersonValidationService } from 'src/app/validation/person-validation.service';

@Component({
  selector: 'app-add-person',
  templateUrl: './add-person.component.html',
  styleUrls: ['./add-person.component.css']
})
export class AddPersonComponent {


  title = 'frontend';
  person!: Person;
  addPersonForm: any;
  
  constructor(private formBuilder: FormBuilder, private personService: PersonService, private personValidation: PersonValidationService){

    this.addPersonForm = this.formBuilder.group({
      id: ['',[Validators.required, Validators.minLength(5)]],
      name: ['',[Validators.required, Validators.minLength(2)]],
      birthday:['',[Validators.required, Validators.minLength(5)]],
    })
  }

  onSubmit() {
    const id = this.addPersonForm.get('id').value;
    const name = this.addPersonForm.get('name').value;
    const birthday = this.addPersonForm.get('birthday').value;

    const pers  = {
      id: id,
      name: name,
      birthday: birthday
    }
    const response = this.personService.createPerson(pers).subscribe({
      next: () => {
        console.log('person created succesfully')
      },
      error: (error) => {
        console.error('Error registering person:', error);
      },
    });
    console.log(response)
    }

  isIdLengthValid(): boolean{
    const id = this.addPersonForm.get('id').value;
    return this.personValidation.isIdLengthValid(id);
  }

  isIdPatternValid(): boolean {
    const id = this.addPersonForm.get('id').value;
    return this.personValidation.isIdPatternValid(id);
  }

  isNameNotEmpty(): boolean {
    const name = this.addPersonForm.get('name').value;
    return this.personValidation.isNameNotEmpty(name);
  }

  isValidDateFormat(): boolean {
    const birthday = this.addPersonForm.get('birthday').value;
    return this.personValidation.isValidDateFormat(birthday);
  }
}


