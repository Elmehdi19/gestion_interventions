export interface ChatbotMessageRequest {
  sessionId?: number;
  clientId?: number;
  message: string;
}

export interface ChatbotMessageResponse {
  sessionId: number;
  response: string;
  intendToRedirect: boolean;
  redirectUrl?: string;
}