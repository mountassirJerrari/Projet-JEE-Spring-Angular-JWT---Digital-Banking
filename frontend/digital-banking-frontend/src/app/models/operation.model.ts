export interface AccountOperation {
  id: number;
  operationDate: Date;
  amount: number;
  type: string;
  description: string;
}

export interface AccountHistory {
  accountId: string;
  balance: number;
  currentPage: number;
  totalPages: number;
  pageSize: number;
  accountOperationDTOS: AccountOperation[];
}

export interface DebitOperation {
  accountId: string;
  amount: number;
  description: string;
}

export interface CreditOperation {
  accountId: string;
  amount: number;
  description: string;
}

export interface TransferOperation {
  accountSource: string;
  accountDestination: string;
  amount: number;
  description: string;
}
