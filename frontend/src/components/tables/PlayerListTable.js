import Table from "./Table";
import { useEffect, useState } from "react";
import PaginationHandler from "../PaginationHandler";
import EditPlayerInfoModal from "../modals/EditPlayerInfoModal";
import AddToTransferListModal from "../modals/AddToTransferListModal";
import {
  getEndPaginationNo,
  getStartPaginationNo,
  playerDisplayFieldsWithType,
} from "../../utils/table.utils";
import { APP_STATE_KEYS } from "../../context/app-state-constants";
import { PAGE_LIMIT } from "../../utils/common.utils";
import useGlobalContext from "../../hooks/useGlobalContext";

export default function PlayerListTable() {
  const [page, setPage] = useState(1);
  const [showEditModal, setShowEditModal] = useState(false);
  const [showSellModal, setShowSellModal] = useState(false);
  const [selectedPlayerId, setSelectedPlayerId] = useState(-1);
  const { getState, service } = useGlobalContext();
  const players = getState(APP_STATE_KEYS.PLAYERS);
  const totalPlayers = getState(APP_STATE_KEYS.TOTAL_PLAYERS);

  // load players info only on page change
  useEffect(() => {
    // load data list
    service.PlayerService.getPlayersInfo(page);

    // load count
    service.PlayerService.getPlayersCount();
  }, [page]);

  return (
    <div className="m-5">
      <h1 className="w-full text-center text-4xl font-bold leading-tight text-gray-900">
        Players
      </h1>

      <div className="mt-5 mb-12">
        <Table
          data={players}
          displayFields={playerDisplayFieldsWithType}
          // these actions will be triggered on on players
          actionList={[
            {
              name: "Edit",
              task: (data) => {
                setSelectedPlayerId(data.playerId);
                setShowEditModal(true);
              },
            },
            {
              name: "Sell",
              task: (data) => {
                setSelectedPlayerId(data.playerId);
                setShowSellModal(true);
              },
              disableCondition: (data) => {
                return data.canTransfer;
              },
            },
          ]}
        />
      </div>

      <PaginationHandler
        start={getStartPaginationNo(page, PAGE_LIMIT)}
        end={getEndPaginationNo(page, PAGE_LIMIT)}
        total={totalPlayers}
        previousClickAction={() => setPage(page - 1)}
        nextClickAction={() => setPage(page + 1)}
      />

      {showEditModal && (
        <EditPlayerInfoModal
          modalCloseAction={() => {
            setShowEditModal(false);
            setSelectedPlayerId(-1);
          }}
          selectedPlayerId={selectedPlayerId}
        />
      )}

      {showSellModal && (
        <AddToTransferListModal
          modalCloseAction={() => {
            setShowSellModal(false);
            setSelectedPlayerId(-1);
          }}
          selectedPlayerId={selectedPlayerId}
        />
      )}
    </div>
  );
}
