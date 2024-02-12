import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HuntService {
  private apiUrl = "http://127.0.0.1:8080/api/hunting"

  constructor(private http:HttpClient) {}

  getHunts(): Observable<any> {
    return this.http.get<any>(this.apiUrl);
  }

  addHunt(hunt: any): Observable<any> {
    return this.http.post(this.apiUrl, hunt);
  }

}
