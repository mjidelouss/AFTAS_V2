import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FishService {
  private apiUrl = "http://127.0.0.1:8080/api/fish"
  constructor(private http:HttpClient) { }

  getFishes(): Observable<any> {
    return this.http.get<any>(this.apiUrl);
  }

  addFish(fish: any): Observable<any> {
    return this.http.post(this.apiUrl, fish);
  }

  deleteFish(id: number): Observable<any> {
    const url = `http://127.0.0.1:8080/api/fish/${id}`;
    return this.http.delete(url);
  }

}
