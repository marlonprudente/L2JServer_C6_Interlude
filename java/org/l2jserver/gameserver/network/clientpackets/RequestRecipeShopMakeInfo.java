/*
 * This file is part of the L2JServer project.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.l2jserver.gameserver.network.clientpackets;

import org.l2jserver.gameserver.model.World;
import org.l2jserver.gameserver.model.actor.instance.PlayerInstance;
import org.l2jserver.gameserver.network.serverpackets.RecipeShopItemInfo;

public class RequestRecipeShopMakeInfo extends GameClientPacket
{
	private int _playerObjectId;
	private int _recipeId;
	
	@Override
	protected void readImpl()
	{
		_playerObjectId = readD();
		_recipeId = readD();
	}
	
	@Override
	protected void runImpl()
	{
		final PlayerInstance player = getClient().getPlayer();
		if (player == null)
		{
			return;
		}
		
		final PlayerInstance shop = World.getInstance().getPlayer(_playerObjectId);
		if ((shop == null) || (shop.getPrivateStoreType() != 5))
		{
			return;
		}
		
		player.sendPacket(new RecipeShopItemInfo(shop, _recipeId));
	}
}
