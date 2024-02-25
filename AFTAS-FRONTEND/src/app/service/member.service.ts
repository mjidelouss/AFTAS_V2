import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MemberService {
  private apiUrl = 'http://127.0.0.1:8080/api/member/all';
  private addApiUrl = 'http://127.0.0.1:8080/api/member';

  constructor(private http: HttpClient) {}

  getMembers(): Observable<any> {
    return this.http.get<any>(this.apiUrl, { withCredentials: true });
  }

  activateAccount(id: number): Observable<any> {
    console.log("entered");
    const url = `http://127.0.0.1:8080/api/v1/activate/${id}`;
    return this.http.post(url, {});
  }

  searchMembers(searchTerm: string): Observable<any> {
    const params = new HttpParams().set('searchTerm', searchTerm);
    return this.http.get<any>(`${this.addApiUrl}`, { params });
  }

  addMember(memberData: any): Observable<any> {
    return this.http.post(this.addApiUrl, memberData);
  }

  updateMember(member: any): Observable<any> {
    const updateApiUrl = `http://127.0.0.1:8080/api/member/${member.id}`;
    return this.http.put(updateApiUrl, member);
  }

  deleteMember(id: number): Observable<any> {
    const url = `http://127.0.0.1:8080/api/member/${id}`;
    return this.http.delete(url);
  }

}
