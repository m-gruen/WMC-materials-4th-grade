import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [JsonPipe],

  template: `
    <h1>Angular 21 + Quarkus (Java 25) + Postgres</h1>

    <button (click)="loadHello()">Call /api/hello</button>
    <pre>{{ hello | json }}</pre>

    <hr />

    <button (click)="loadGreetings()">Load DB greetings</button>
    <button (click)="addGreeting()">Add greeting to DB</button>
    <pre>{{ greetings | json }}</pre>
  `
})
export class AppComponent {
  hello: any;
  greetings: any;

  constructor(private http: HttpClient) {}

  loadHello() {
    this.http.get('/helloApp/api/hello').subscribe(r => this.hello = r);
  }

  loadGreetings() {
    this.http.get('/helloApp/api/greetings').subscribe(r => this.greetings = r);
  }

  addGreeting() {
    this.http.post('/helloApp/api/greetings', { message: 'Hello row from Angular 21' })
      .subscribe(_ => this.loadGreetings());
  }
}
