import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RankService {

  private registerApiUrl = 'http://127.0.0.1:8080/api/ranking/register';
  private podiumApiUrl = 'http://127.0.0.1:8080/api/ranking/podium';

  constructor(private http: HttpClient) {}

  registerMemberToCompetition(data: any): Observable<any> {
    return this.http.post(this.registerApiUrl, data);
  }

  getWinners(data: any): Observable<any> {
    return this.http.post(this.podiumApiUrl, data);
  }

}
