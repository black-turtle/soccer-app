import Table from "./Table";
import { useEffect, useState } from "react";
import PaginationHandler from "../PaginationHandler";
import {
  getEndPaginationNo,
  getStartPaginationNo,
  transferDisplayFieldsWithType,
} from "../../utils/table.utils";
import TransferModal from "../modals/TransferModal";
import { APP_STATE_KEYS } from "../../context/app-state-constants";
import { PAGE_LIMIT } from "../../utils/common.utils";
import { USER_NAME } from "../../utils/auth.utils";
import { InformationCircleIcon } from "@heroicons/react/outline";
import useGlobalContext from "../../hooks/useGlobalContext";

export default function TransferListTable() {
  const [page, setPage] = useState(1);
  const [showTransferModal, setShowTransferModal] = useState(false);
  const [selectedTransferId, setSelectedTransferId] = useState(-1);
  const { getState, service } = useGlobalContext();
  const transferList = getState(APP_STATE_KEYS.PLAYERS);
  const totalTransfers = getState(APP_STATE_KEYS.TOTAL_TRANSFERS);
  const userName = localStorage.getItem(USER_NAME);

  // load transfer info only on page change
  useEffect(() => {
    service.TransferService.getTransferList(page);

    // load count
    service.TransferService.getTransferCount();
  }, [page]);

  return (
    <div className="m-5">
      <h1 className="w-full text-center text-4xl font-bold leading-tight text-gray-900">
        Transfer List
      </h1>

      <div className="mt-5 mb-12">
        <div className="mb-3">
          <UserBuyAlert />
        </div>

        <Table
          data={transferList}
          displayFields={transferDisplayFieldsWithType}
          actionList={[
            {
              name: "Buy",
              task: (row) => {
                setSelectedTransferId(row.transferId);
                setShowTransferModal(true);
              },
              disableCondition: (data) => {
                return data.owner === userName;
              },
            },
          ]}
        />
      </div>

      <PaginationHandler
        start={getStartPaginationNo(page, PAGE_LIMIT)}
        end={getEndPaginationNo(page, PAGE_LIMIT)}
        total={totalTransfers}
        previousClickAction={() => setPage(page - 1)}
        nextClickAction={() => setPage(page + 1)}
      />

      {showTransferModal && (
        <TransferModal
          modalCloseAction={() => {
            setShowTransferModal(false);
            setSelectedTransferId(-1);
          }}
          selectedTransferId={selectedTransferId}
        />
      )}
    </div>
  );
}

function UserBuyAlert() {
  return (
    <div className="rounded-md bg-blue-50 p-4">
      <div className="flex">
        <div className="flex-shrink-0">
          <InformationCircleIcon
            className="h-5 w-5 text-blue-400"
            aria-hidden="true"
          />
        </div>
        <div className="ml-3 flex-1 md:flex md:justify-between">
          <p className="text-sm text-blue-700">
            Note: You can't buy your already owned players. So buy option is
            disabled for those.
          </p>
        </div>
      </div>
    </div>
  );
}
